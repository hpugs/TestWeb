package com.hpugs.testweb.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:42
 * @Description：
 */
@AllArgsConstructor
@Getter
public enum FileHandleTaskStatusEnum {

    INIT(1, "处理中"),
    SUCCESS(2, "已完成"),
    FAIL(3, "异常终结");

    private final int status;

    private final String desc;

    public static String getDesc(int status) {
        for (FileHandleTaskStatusEnum taskStatusEnum : FileHandleTaskStatusEnum.values()) {
            if (taskStatusEnum.getStatus() == status) {
                return taskStatusEnum.getDesc();
            }
        }
        return null;
    }

}
