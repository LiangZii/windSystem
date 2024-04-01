package com.iot.mywind.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Json格式的数据进行响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<E> implements Serializable {
    /** 状态码 */
    private Integer state;
    /** 数据 */
    private E data;
    /** 描述信息 */
    private String message;

    public JsonResult(Integer state,String message){
        this.state = state;
        this.message = message;
    }

}
