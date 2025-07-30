package com.hpugs.testweb.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hpugs.testweb.excel.enums.FileEnum;
import com.hpugs.testweb.excel.handle.FileHandlerFactory;
import com.hpugs.testweb.excel.handle.IFileHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:09
 * @Description：
 */
@Service
public class ExcelService {

    @Resource
    private FileHandlerFactory fileHandlerFactory;

    public void downloadFileTemplate(Integer fileType, HttpServletResponse response) {
        try {
            Assert.notNull(fileType, "文件类型不能为空");
            FileEnum fileEnum = FileEnum.getFileEnum(fileType);
            Assert.notNull(fileType, "文件类型非法");

            String templateFileName = fileEnum.getTemplateFileName();
            Class<?> excelClass = fileEnum.getExcelClass();

            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode(templateFileName, StandardCharsets.UTF_8.name());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

            // 生成第一个sheet页导入表头
            WriteSheet oneSheet = EasyExcel.writerSheet(0, "导入数据").head(excelClass).build();
            excelWriter.write(null, oneSheet);

            // 生成第二个sheet页写入说明
            Class<?> importDescClass = fileEnum.getImportDescClass();
            if (importDescClass != null) {
                IFileHandler fileHandler = fileHandlerFactory.getFileHandler(fileEnum);
                WriteSheet twoSheet = EasyExcel.writerSheet(1, "导入说明").head(fileEnum.getImportDescClass()).build();
                excelWriter.write(fileHandler.getImportDesc(), twoSheet);
            }

            excelWriter.finish();
        } catch (Exception e) {
            errorResponse(response, e.getMessage());
        }
    }

    private void errorResponse(HttpServletResponse response, String message) {
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader("Content-Type", "application/text/plain");
            ServletOutputStream outputStream = response.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8.name());
            outputStreamWriter.write(message);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
