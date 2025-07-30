package com.hpugs.testweb.excel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:08
 * @Description：
 */
@Data
public class UserExcelVO extends BaseExcel {

    @ExcelIgnore
    private String id;

    @ExcelProperty(value = "账号", index = 0, order = 0)
    @ColumnWidth(20)
    private String account;

    @ExcelProperty(value = "姓名", index = 1, order = 1)
    @ColumnWidth(15)
    private String name;

    @ExcelProperty(value = "年龄", index = 2, order = 2)
    @ColumnWidth(10)
    private Integer age;

    @ExcelProperty(value = "地址", index = 3, order = 3)
    @ColumnWidth(40)
    private String address;

    @ExcelProperty(value = "手机号", index = 4, order = 4)
    @ColumnWidth(20)
    private String phone;

    @ExcelProperty(value = "邮箱", index = 5, order = 5)
    @ColumnWidth(20)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelProperty(value = "创建时间", index = 6, order = 6)
    @ColumnWidth(20)
    private LocalDateTime createTime;

}
