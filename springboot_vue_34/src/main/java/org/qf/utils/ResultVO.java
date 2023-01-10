package org.qf.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回信息的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {

    //响应的编码
    private int code;


    //响应的信息
    private String msg;


    //响应的数据
    private Object data;


}
