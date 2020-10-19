package com.raven.api.encryption.controller;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.data.EncryptionData;
import com.raven.api.encryption.util.AESUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-17 15:50
 */
@RestController
public class AESController {

    @Value(value = "${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_AES_PUB_KEY+"}")
    private String aesPubKey;

    @PostMapping(value = Routes.AES_DECRYPTION)
    public String decryption(@RequestBody @Validated EncryptionData encryptionData) {
        try {
            return AESUtils.decryption(encryptionData.getValue(), aesPubKey);
        }catch (Exception e) {
            return "解密失败";
        }
    }

    @PostMapping(value = Routes.AES_ENCRYPTION)
    public String encryption(@RequestBody @Validated EncryptionData encryptionData) {
        try {
            return AESUtils.encryption(encryptionData.getValue(), aesPubKey);
        }catch (Exception e) {
            return "加密失败";
        }
    }
}
