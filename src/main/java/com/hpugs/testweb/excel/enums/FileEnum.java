package com.hpugs.testweb.excel.enums;

import com.hpugs.testweb.excel.model.UserExcelVO;
import com.hpugs.testweb.excel.model.desc.UserDescExcelVO;
import com.hpugs.testweb.excel.model.result.UserErrorExcelVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:34
 * @Description：
 */
@AllArgsConstructor
@Getter
public enum FileEnum {

    IMPORT_USER_EXCEL(1, "importUserExcelHandler", "批量导入用户", "批量导入用户模版.xlsx", UserExcelVO.class, UserDescExcelVO.class, UserErrorExcelVO.class, 1000, true)
    ;

    /**
     * 文件类型
     */
    private final Integer fileType;

    /**
     * 平台
     */
    private final String handler;

    /**
     * 批量导入文件描述
     */
    private final String fileName;

    /**
     * 模版文件名称
     */
    private final String templateFileName;

    /**
     * 导入类型数据模板
     */
    private final Class<?> excelClass;

    /**
     * 导入说明模板类
     */
    private final Class<?> importDescClass;

    /**
     * 导入类型数据模板
     */
    private final Class<?> errorMsgExcelClass;

    /**
     * 导入最大行数
     */
    private final Integer importMaxSize;

    /**
     * 是否异步处理
     */
    private final boolean isAsyncHandle;

    public static FileEnum getFileEnum(Integer fileType) {
        for (FileEnum value : FileEnum.values()) {
            if (value.getFileType().equals(fileType)) {
                return value;
            }
        }
        return null;
    }

    public static String getFileName(Integer fileType) {
        for (FileEnum value : FileEnum.values()) {
            if (value.getFileType().equals(fileType)) {
                return value.getFileName();
            }
        }
        return "未知";
    }

}
