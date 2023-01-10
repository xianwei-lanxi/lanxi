package org.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体类对应user表
 */
@Data  //相当于set和get方法
@AllArgsConstructor //有参构造
@NoArgsConstructor  //无参构造
@Table(name = "user")  //对应的表名
public class User {
    @Id  // 代表主键
    private Integer id;  // 编号

    private String username;  // 姓名

    private String password;  //密码

    private String sex;  //性别

    private String address;  //地址

    private String phone;  // 电话
    // alt+enter
    @JsonFormat(pattern = "yyyy-MM-dd")  //日期格式化
    private Date birthday;  // 出生日期

    private Date createtime;  //创建时间

    private Date updatetime;  //修改时间

    private String imgs; //图片

    private Integer sbs; //打榜数
}
