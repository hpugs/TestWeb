package com.hpugs.testweb.excel.handle;

import com.hpugs.testweb.excel.enums.FileHandleTaskStatusEnum;
import org.apache.rocketmq.shaded.commons.lang3.RandomUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:38
 * @Description：
 */
public abstract class AbstractFileHandler<T, D> implements IFileHandler<D> {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public FileHandleResultVO handle(MultipartFile file) {
        // 前置校验
        checkPrefix(file);

        String fileName = file.getOriginalFilename();

        // 生成任务ID, 用于记录日志，便于后续查询
        Long taskId = RandomUtils.nextLong(0, Long.MAX_VALUE);

        ByteArrayOutputStream baos = cloneInputStream(file);
        // 打开1个新输入流
        InputStream readDataStream = new ByteArrayInputStream(baos.toByteArray());

        // 解析文件数据
        List<T> dataList = readFileData(readDataStream);

        // 处理文件数据
        if (getFileType().isAsyncHandle()) {
            // 异步处理
            asyncDealData(taskId, fileName, dataList);
            return new FileHandleResultVO(FileHandleTaskStatusEnum.SUCCESS.getStatus(), "导入文件已提交，请稍后在批处理日志查看导入结果。", null);
        } else {
            // 同步处理
            return dealData(taskId, fileName, dataList);
        }
    }

    protected abstract List<T> readFileData(InputStream readDataStream);

    private void asyncDealData(Long taskId, String fileName, List<T> list) {
        threadPoolTaskExecutor.execute(() -> {
            dealData(taskId, fileName, list);
        });
    }

    protected abstract FileHandleResultVO dealData(Long taskId, String fileName, List<T> list);

    private static ByteArrayOutputStream cloneInputStream(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            if (inputStream == null) {
                throw new RuntimeException("服务暂不可用，请稍后重试或联系管理员");
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            throw new RuntimeException("文件读取异常，请稍后重试或联系管理员");
        }
    }

}
