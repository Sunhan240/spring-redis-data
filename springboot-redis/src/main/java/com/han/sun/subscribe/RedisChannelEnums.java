package com.han.sun.subscribe;

import com.han.sun.subscribe.impl.LiveChangeSubscribeImpl;
import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * TODO(枚举定义管道)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 10:20
 */
@Getter
public enum RedisChannelEnums {

    /**
     * redis频道code定义  需要与发布者一致
     */
    LIVE_INFO_CHANGE("LIVE_INFO_CHANGE", LiveChangeSubscribeImpl.class, "直播消息改变"),;

    /**
     * 枚举定义+描述
     */
    private String code;

    private Class<? extends RedisSubscribe> className;

    private String description;

    RedisChannelEnums(String code, Class<? extends RedisSubscribe> className, String description) {
        this.code = code;
        this.className = className;
        this.description = description;
    }

    public static RedisChannelEnums getEnum(String code) {
        RedisChannelEnums[] values = RedisChannelEnums.values();
        if (null != code && values.length > 0) {
            for (RedisChannelEnums value : values) {
                if (value.code.equalsIgnoreCase(code)) {
                    return value;
                }
            }
        }
        return null;
    }


    /**
     * 该code在枚举列表code属性是否存在
     *
     * @param code code
     * @return boolean
     */
    public static boolean containsCode(String code) {
        RedisChannelEnums anEnum = getEnum(code);
        return anEnum != null;
    }


    public static boolean equals(String code, RedisChannelEnums channelEnums) {
        return channelEnums.code.equalsIgnoreCase(code);
    }
}
