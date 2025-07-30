package com.hpugs.testweb.excel.model.desc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.hpugs.testweb.excel.model.UserExcelVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:10
 * @Description：
 */
@HeadStyle
@ContentStyle(wrapped = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDescExcelVO extends UserExcelVO {

    @ColumnWidth(25)
    @ExcelProperty(value = "模版字段", index = 0)
    private String name;

    @ColumnWidth(25)
    @ExcelProperty(value = "是否必填", index = 1)
    private String need;

    @ColumnWidth(40)
    @ExcelProperty(value = "说明示例", index = 2)
    private String simple;

}


