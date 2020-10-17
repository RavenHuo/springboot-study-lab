package com.raven.api.encryption.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-17 15:51
 */
public class Routes {
    public static final String AES = "/aes";
    /**
     * AES 加密密文
     */
    public static final String AES_ENCRYPTION = AES + "/encryption";


    /**
     * AES 解密密文
     */
    public static final String AES_DECRYPTION = AES + "/decryption";


    public static final String RSA = "/rsa";

    /**
     * RSA 随机公钥私钥
     */
    public static final String RSA_RANDOM_KEY = RSA + "/random/key";

    /**
     * RSA 公钥
     */
    public static final String RSA_PUB_KEY = RSA + "/pub/key";

    /**
     * AES 加密密文
     */
    public static final String RSA_ENCRYPTION = RSA + "/encryption";

    /**
     * AES 解密密文
     */
    public static final String RSA_DECRYPTION = RSA + "/decryption";


    public static final List<String> ENCRYPTION_EXCLUDE_URL = new ArrayList<>();

    static {
        ENCRYPTION_EXCLUDE_URL.add(AES_ENCRYPTION);
        ENCRYPTION_EXCLUDE_URL.add(AES_DECRYPTION);
        ENCRYPTION_EXCLUDE_URL.add(RSA_RANDOM_KEY);
        ENCRYPTION_EXCLUDE_URL.add(RSA_PUB_KEY);
        ENCRYPTION_EXCLUDE_URL.add(RSA_ENCRYPTION);
        ENCRYPTION_EXCLUDE_URL.add(RSA_DECRYPTION);
    }

}
