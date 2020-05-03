/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/3 16:16
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.eastrobot.robotdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/3 16:16
 */

public class SerializeUtils {

    private static final Logger log = LogManager.getLogger(SerializeUtils.class);

    /**
     * 【序列化对象】
     *
     * @param object javabean
     * @return 节流流
     * @since 2020/5/3 16:17 han.sun
     */
    public static byte[] serizlize(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 【反序列化对象】
     *
     * @param bytes 字节流数组
     * @return javaben对象
     * @since 2020/5/3 16:18 han.sun
     */
    public static <T> T deserialize(byte[] bytes, Class<T> cls) {

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            Object object = ois.readObject();
            return cls.cast(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
