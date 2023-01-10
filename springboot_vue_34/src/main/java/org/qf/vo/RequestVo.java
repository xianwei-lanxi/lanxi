package org.qf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qin
 * @create 2022--12--16--14:55
 * 这个一个请求参数的实体
 * 根据请求参数，有啥就用啥，没有就删掉
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVo {
    private Integer id;  // 编号

    private String username;  // 姓名

    private String sex;  //性别

    private String address;  //地址

    private String phone;  // 电话



//    发生转换异常，原因是前端传过来的格式是String类型的，所以此处实体使用数据类型是String
    private String birthday;  // 出生日期
}
