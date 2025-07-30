package com.hpugs.testweb.excel.model.result;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.hpugs.testweb.excel.model.UserExcelVO;
import lombok.Data;

/**
 * @Author：xinge
 * @Date：2024/12/27 11:21
 * @Description：
 */
@Data
public class UserErrorExcelVO extends UserExcelVO {

    @ExcelProperty(value = "处理结果")
    @HeadStyle(fillBackgroundColor = 40)
    @ColumnWidth(40)
    private String result;

}
