package com.raven.api.encryption.util;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.data.RsaEncryptionKey;
import lombok.extern.slf4j.Slf4j;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-15 15:16
 */
@Slf4j
public class RSAUtils {


    /**
     * 公钥加密
     *
     * @param content   数据
     * @param publicKey 公钥
     * @return
     */
    public static String encryption(String content, String publicKey) throws Exception{
        try{
            //base64编码的公钥
            byte[] decoded = BaseByteUtil.base642Byte(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(ApiEncryptionConstant.RSA_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance(ApiEncryptionConstant.RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return BaseByteUtil.byte2Base64(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e) {
            log.error("使用公钥加密出错 ", e);
            throw e;
        }
    }

    /**
     * 私钥解密
     *
     * @param content    需要解密的数据
     * @param privateKey 私钥
     * @return
     */
    public static String decryption(String content, String privateKey) throws Exception{
        try {
            //64位解码加密后的字符串
            byte[] inputByte = BaseByteUtil.base642Byte(content);

            //base64编码的私钥
            byte[] decoded = BaseByteUtil.base642Byte(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(ApiEncryptionConstant.RSA_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance(ApiEncryptionConstant.RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            log.error("使用私钥对数据解密异常", e);
            throw e;
        }

    }


    /**
     * 获取随机的公钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RsaEncryptionKey genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ApiEncryptionConstant.RSA_ALGORITHM);
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        String publicKey = BaseByteUtil.byte2Base64(keyPair.getPublic().getEncoded());
        // 得到私钥字符串
        String privateKey = BaseByteUtil.byte2Base64(keyPair.getPrivate().getEncoded());

        RsaEncryptionKey rsaEncryptionKey = new RsaEncryptionKey();
        rsaEncryptionKey.setPrivateKey(privateKey);
        rsaEncryptionKey.setPublicKey(publicKey);
        return rsaEncryptionKey;
    }

    public static void main(String[] args) throws Exception {
        RsaEncryptionKey key = genKeyPair();
        String content = "{\n" +
                "\"name\":\"raven\",\n" +
                "\"age\":18\n" +
                "}";
        System.out.println(" 加密前=   " + content);
        String encryptionStr = encryption(content, key.getPublicKey());
        System.out.println(" 加密后=   " + encryptionStr);
        String decryptionStr = decryption(encryptionStr, key.getPrivateKey());
        System.out.println(" 解密后=   " + decryptionStr);
    }
}
