package org.hzt.springscaffold.user.filter;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 数据库密码工厂类
 * */
public class DBPasswordFactory implements FactoryBean<String> {

    private static final String LOCAL_KEY_MARK      = "@";
    private static final String LOCAL_KEY_FILE_PATH = "/root/private/dbsecretkey";
    private static final String DEFAULT_KEY         = "strong";

    /**
     * 密文
     */
    @Setter
    private String              password;

    /**
     * 使用默认值即可
     */
    @Setter
    private String              localKeyFilePath    = LOCAL_KEY_FILE_PATH;

    @Override
    public String getObject() throws Exception {
        return password == null ? null : decode(password, localKeyFilePath);
    }

    @Override
    public Class<String> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private static String decode(String secret, String localKeyFilePath) throws Exception {
        String keyString = secret.startsWith(LOCAL_KEY_MARK) ? readKeyFromLocalFile(localKeyFilePath) : DEFAULT_KEY;
        String secretString = secret.startsWith(LOCAL_KEY_MARK) ? secret.substring(LOCAL_KEY_MARK.length()) : secret;
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyString.getBytes(), "Blowfish"));
        byte[] decode = cipher.doFinal(new BigInteger(secretString, 16).toByteArray());
        return new String(decode);
    }

    private static String readKeyFromLocalFile(String localFile) throws IOException {
        if (localFile == null || localFile.trim().length() == 0) {
            throw new IllegalArgumentException("密码加解密采用本地Key文件方式，但是文件路径被设置为空，请检查!");
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(localFile));
        } catch (FileNotFoundException ex) {
            throw new IllegalStateException(String.format("密码加解密采用本地Key文件方式，但是文件%s不存在或者无访问权限，请联系运维配置!", localFile));
        }
        String keyString = reader.readLine();
        if (keyString == null || (keyString = keyString.trim()).length() == 0) {
            throw new IllegalStateException(String.format("密码加解密采用本地Key文件方式，但是文件%s内容为空，请联系运维配置!", localFile));
        }
        return keyString;
    }

}
