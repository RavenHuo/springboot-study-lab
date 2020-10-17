package com.raven.api.encryption.common;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-13 09:17
 */
public class ApiEncryptionConstant {

    public static final String SPRING_API_ENCRYPTION = "spring.api.encryption";

    /**
     * 开关
     */
    public static final String SPRING_API_ENCRYPTION_SWITCH = SPRING_API_ENCRYPTION + ".switch";

    /**
     * 公钥
     */
    public static final String SPRING_API_ENCRYPTION_RSA_PUB_KEY = SPRING_API_ENCRYPTION + ".rsa.pubKey";

    /**
     * 私钥
     */
    public static final String SPRING_API_ENCRYPTION_RSA_PRI_KEY = SPRING_API_ENCRYPTION + ".rsa.priKey";

    /**
     * AES 公钥
     */
    public static final String SPRING_API_ENCRYPTION_AES_PUB_KEY = SPRING_API_ENCRYPTION + ".aes.pubKey";


    public static final String RSA_ALGORITHM = "RSA";

    public static final String AES_ALGORITHM = "AES";
}
