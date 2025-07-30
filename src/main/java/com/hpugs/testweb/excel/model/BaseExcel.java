package com.hpugs.testweb.excel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:10
 * @Description：
 */
@Data
public class BaseExcel extends BaseRowModel {

    @ExcelIgnore
    private String result;

}
