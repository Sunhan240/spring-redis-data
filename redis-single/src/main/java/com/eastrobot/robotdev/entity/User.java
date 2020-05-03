/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/3 15:34
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.eastrobot.robotdev.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/3 15:34
 */
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private String userId;

    private String username;

    private String password;

    private String address;

    private Integer age;

    public User(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
