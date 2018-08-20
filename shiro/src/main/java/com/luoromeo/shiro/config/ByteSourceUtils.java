package com.luoromeo.shiro.config;

import org.apache.shiro.util.ByteSource;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月20日 09:34
 * @modified By
 */
public enum ByteSourceUtils {

    INSTANCE;

    public ByteSource bytes(byte[] bytes) {
        return new MySimpleByteSource(bytes);
    }

    public ByteSource bytes(String arg0) {
        return new MySimpleByteSource(arg0.getBytes());
    }
}
