package com.hpugs.testweb.excel.handle.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hpugs.testweb.excel.enums.FileHandleTaskStatusEnum;
import com.hpugs.testweb.excel.handle.AbstractFileHandler;
import com.hpugs.testweb.excel.handle.FileHandleResultVO;
import com.hpugs.testweb.excel.model.BaseExcel;
import com.hpugs.testweb.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.shaded.commons.lang3.StringUtils;
import org.apache.rocketmq.shaded.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:48
 * @Description：
 */
@Slf4j
public abstract class AbstractExcelFileHandler<T extends BaseExcel, D> extends AbstractFileHandler<T, D> {

    @Override
    public void checkPrefix(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }

        // 检查文件类型
        String originalFilename = file.getOriginalFilename();
        if (!originalFilename.endsWith(ExcelTypeEnum.XLSX.getValue()) && !originalFilename.endsWith(ExcelTypeEnum.XLS.getValue())) {
            throw new RuntimeException("文件格式仅支持.xlsx或.xls");
        }
    }

    @Override
    protected List<T> readFileData(InputStream readDataStream) {
        List<T> dataList = null;
        try {
            dataList = EasyExcelFactory.read(readDataStream, getFileType().getExcelClass(), null).sheet(0).doReadSync();
        } catch (Exception e) {
            log.error("文件解析错误！{}", ExceptionUtils.getMessage(e));
            throw new RuntimeException("Excel文件解析异常！");
        }

        if (CollectionUtils.isEmpty(dataList) || dataList.size() == 1) {
            throw new RuntimeException("Excel文件内容不能空！");
        }

        Integer importMaxSize = getFileType().getImportMaxSize();
        if (dataList.size() > importMaxSize) {
            throw new RuntimeException(String.format("每次导入最多%d条！", importMaxSize));
        }
        return dataList;
    }

    @Override
    protected FileHandleResultVO dealData(Long taskId, String fileName, List<T> dataList) {
        // 遍历处理数据
        log.info("file处理任务id=>{}，任务开始处理", taskId);
        for (T t : dataList) {
            try {
                String result = dealRow(t);
                if (StringUtils.isNotBlank(result)) {
                    t.setResult(result);
                }
            } catch (Exception e) {
                log.info("file处理任务id=>{}，处理异常", taskId, e);
                t.setResult("处理异常，请稍后重试或联系管理员");
            }
        }
        log.info("file处理任务id=>{}，任务处理结束", taskId);

        // 上传处理结果到Oss
        try {
            String targetFileUrl = uploadTaskResult(dataList, fileName);
            log.info("批量导入任务结果上传完成 fileType=>{} taskId=>{} sourceFileUrl=>{}, targetFileUrl=>{}", getFileType(), taskId, fileName, targetFileUrl);
            return new FileHandleResultVO(FileHandleTaskStatusEnum.SUCCESS.getStatus(), "success", targetFileUrl);
        } catch (Exception e) {
            log.error("批量导入任务结果上传失败 fileType=>{} taskId=>{} sourceFileUrl=>{}", getFileType(), taskId, fileName, e);
        }
        return new FileHandleResultVO(FileHandleTaskStatusEnum.FAIL.getStatus(), String.format("任务Id=>%d, 处理结果上传异常，请联系管理员排查", taskId), null);
    }

    protected abstract String dealRow(T t);

    private String uploadTaskResult(List<T> dataList, String sourceFileName) {
        // 生成错误文件，并上传oss
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(out).build();
        // 生成第一个sheet页导入表头
        WriteSheet oneSheet = EasyExcel.writerSheet(0, "导入结果").head(getFileType().getErrorMsgExcelClass()).build();
        excelWriter.write(dataList, oneSheet);
        excelWriter.finish();
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));

        String errorExcelName = FileUtil.getErrorExcelName(sourceFileName);
        return errorExcelName;
////        String targetUrlPath = uploadFileToOss(dataInputStream, errorExcelName, (long) out.size());
//        if (StringUtils.isBlank(targetUrlPath)) {
//            throw new TimelyException(ExceptionEnums.SER_ERR_BUSINESS.getErrorCode(), "结果文件上传失败");
//        }
//        return targetUrlPath;
    }

}
