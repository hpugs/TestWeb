package com.hpugs.testweb.excel.handle.excel.handler;

import com.alibaba.excel.util.StringUtils;
import com.hpugs.testweb.excel.enums.FileEnum;
import com.hpugs.testweb.excel.handle.excel.AbstractExcelFileHandler;
import com.hpugs.testweb.excel.model.UserExcelVO;
import com.hpugs.testweb.excel.model.desc.UserDescExcelVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：xinge
 * @Date：2024/12/27 14:07
 * @Description：
 */
@Service
@Component
public class ImportUserHandler extends AbstractExcelFileHandler<UserExcelVO, UserDescExcelVO> {

    @Override
    public FileEnum getFileType() {
        return FileEnum.IMPORT_USER_EXCEL;
    }

    @Override
    public List<UserDescExcelVO> getImportDesc() {
        List<UserDescExcelVO> userDescExcelVOList = new ArrayList<>();
        userDescExcelVOList.add(new UserDescExcelVO("账号", "是", ""));
        userDescExcelVOList.add(new UserDescExcelVO("姓名", "是", "小米"));
        userDescExcelVOList.add(new UserDescExcelVO("年龄", "否", "18"));
        userDescExcelVOList.add(new UserDescExcelVO("地址", "否", ""));
        userDescExcelVOList.add(new UserDescExcelVO("手机号", "是，手机号与邮箱不能同时为空", ""));
        userDescExcelVOList.add(new UserDescExcelVO("邮箱", "是，手机号与邮箱不能同时为空", "xxx@163.com"));
        return userDescExcelVOList;
    }

    @Override
    protected String dealRow(UserExcelVO userExcelVO) {
        String errorMsg = checkParam(userExcelVO);
        if (!StringUtils.isEmpty(errorMsg)) {
            return errorMsg;
        }

        return "success";
    }

    private String checkParam(UserExcelVO vo) {
        return null;
    }
}
