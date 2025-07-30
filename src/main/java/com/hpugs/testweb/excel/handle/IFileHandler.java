package com.hpugs.testweb.excel.handle;

import com.hpugs.testweb.excel.enums.FileEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:34
 * @Description：
 */
public interface IFileHandler<D> {

    FileEnum getFileType();

    List<D> getImportDesc();

    void checkPrefix(MultipartFile file);

    FileHandleResultVO handle(MultipartFile file);

}
