/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/21 5:43 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/21 5:43 PM
 */
@Getter
@Setter
public class DeveloperBook implements Serializable {

    private String articleId;

    private String abstractContent;

    private String category;

    private String page;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
