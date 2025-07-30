package com.hpugs.testweb.excel.handle;

import com.hpugs.testweb.excel.enums.FileEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:37
 * @Description：
 */
@Configuration
public class FileHandlerFactory {

    private static Map<FileEnum, IFileHandler> FILE_HANDLER_MAP = new HashMap<>();

    @Resource
    private void init(List<IFileHandler> fileHandlerList) {
        if(CollectionUtils.isEmpty(fileHandlerList)) {
            return;
        }

        for (IFileHandler iFileHandler : fileHandlerList) {
            FILE_HANDLER_MAP.put(iFileHandler.getFileType(), iFileHandler);
        }
    }

    public IFileHandler getFileHandler(FileEnum fileEnum) {
        return FILE_HANDLER_MAP.get(fileEnum);
    }

}
