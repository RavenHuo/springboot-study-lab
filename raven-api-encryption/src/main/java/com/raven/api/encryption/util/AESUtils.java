package com.raven.api.encryption.util;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @description: 对称加解密工具类
 * @author: huorw
 * @create: 2020-10-15 16:43
 */
@Slf4j
public class AESUtils {

    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encryption(String content, String key) throws Exception{
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, genSecretKeySpec(key));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return BaseByteUtil.byte2Base64(result);

        } catch (Exception e) {
            log.info("AES 加密出错  content=={}  key=={}", content, key);
            throw e;
        }
    }

    /**
     * 解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decryption(String content, String key) throws Exception{
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, genSecretKeySpec(key));

            //执行操作
            byte[] result = cipher.doFinal(BaseByteUtil.base642Byte(content));

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info("AES 解密出错  content=={}  key=={}", content, key);
            throw e;
        }
    }

    public static SecretKeySpec genSecretKeySpec(String key) throws Exception{
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(ApiEncryptionConstant.AES_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), ApiEncryptionConstant.AES_ALGORITHM);

        } catch (Exception e) {
            log.info("生成AES key 出错 e={}",e);
            throw e;
        }

    }

    public static void main(String[] args) throws Exception{
        String pubKey = "qwertyuioo--=098=--==0bgsdbgh";
        String content = "18";
        System.out.println("加密前  content=    "+content);
        String encryptionStr = encryption(content, pubKey);
        System.out.println("加密后  content=    "+encryptionStr);
        String decryptionStr = decryption(encryptionStr, pubKey);
        System.out.println("解密  content=    "+decryptionStr);
    }
}
