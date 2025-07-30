package com.hpugs.testweb.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.shaded.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:59
 * @Description：
 */
@Slf4j
public class FileUtil {

    public static String subFileName(String fileName, int maxFileNameLength) {
        if(StringUtils.isBlank(fileName) || maxFileNameLength <= 0) {
            return fileName;
        }

        try {
            fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("文件名解码失败 fileName->{}", fileName, e);
        }

        String[] split = StringUtils.split(fileName, '.');
        if(split.length > 2) {
            throw new RuntimeException("文件名不合法");
        }
        split[0] = split[0].length() <= maxFileNameLength ? split[0] : split[0].substring(0, maxFileNameLength);
        return StringUtils.join(split, ".");
    }

    public static String getErrorExcelName(String fileName) {
        if (fileName.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            return StringUtils.replace(fileName, ExcelTypeEnum.XLSX.getValue(), "_error.xlsx");
        } else if (fileName.endsWith(ExcelTypeEnum.XLS.getValue())) {
            return StringUtils.replace(fileName, ExcelTypeEnum.XLS.getValue(), "_error.xls");
        } else {
            String[] split = StringUtils.split(fileName, '.');
            return split[0] + "_error." + split[1];
        }
    }

}
