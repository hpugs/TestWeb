package com.hpugs.testweb.excel.handle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：xinge
 * @Date：2024/12/27 13:37
 * @Description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileHandleResultVO {

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 结果描述
     */
    private String resultMag;

    /**
     * 结果的路径
     */
    private String resultPath;

}
