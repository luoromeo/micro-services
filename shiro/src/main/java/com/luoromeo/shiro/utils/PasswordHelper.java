package com.luoromeo.shiro.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.luoromeo.shiro.entity.User;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:09
 * @modified By
 */
public final class PasswordHelper {

    private final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private PasswordHelper() {
        if (SingletonHolder.instance != null) {
            throw new IllegalStateException();
        }
    }

    private static class SingletonHolder {
        private static PasswordHelper instance = new PasswordHelper();
    }

    public static PasswordHelper getInstance() {
        return SingletonHolder.instance;
    }

    public void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String algorithmName = "md5";
        int hashIterations = 2;
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }


}
