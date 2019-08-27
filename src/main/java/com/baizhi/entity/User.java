package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @ExcelIgnore
    private String id;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "图片", type = 2, width = 40, height = 30)
    private String headPic;
    @Excel(name = "用户名")
    private String name;
    @Excel(name = "法号")
    private String dharma;
    @Excel(name = "性别", replace = {"男_1", "女_2"})
    private String sex;
    @Excel(name = "省")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "注册时间", format = "yyyy-MM-dd")
    private Date createDate;
}
