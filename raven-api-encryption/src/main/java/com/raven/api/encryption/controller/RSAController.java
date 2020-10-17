package com.raven.api.encryption.controller;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.data.EncryptionData;
import com.raven.api.encryption.util.RSAUtils;
import com.raven.api.encryption.data.RsaEncryptionKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-17 15:50
 */
@RestController
@Slf4j
public class RSAController {

    @Value(value = "${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_RSA_PRI_KEY+"}")
    private String rsaPriKey;

    @Value(value = "${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_RSA_PUB_KEY+"}")
    private String rsaPubKey;

    @PostMapping(value = Routes.RSA_DECRYPTION)
    public String decryption(@RequestBody @Validated EncryptionData encryptionData) {
        try {
            return RSAUtils.decryption(encryptionData.getValue(), rsaPriKey);
        }catch (Exception e) {
            return "解密失败";
        }
    }

    @PostMapping(value = Routes.RSA_ENCRYPTION)
    public String encryption(@RequestBody @Validated EncryptionData encryptionData) {
        try {
            return RSAUtils.encryption(encryptionData.getValue(), rsaPubKey);
        }catch (Exception e) {
            return "加密失败";
        }
    }

    @GetMapping(value = Routes.RSA_PUB_KEY)
    public String rsaPubKey() {
        return rsaPubKey;
    }

    @GetMapping(value = Routes.RSA_RANDOM_KEY)
    public RsaEncryptionKey rsaRandomKey() {
        try {
            return RSAUtils.genKeyPair();
        }catch (Exception e) {
            log.info("获取随机密钥出错");
            return null;

        }
    }
}
