package com.raven.api.encryption.data;

import lombok.Data;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-15 22:44
 */
@Data
public class RsaEncryptionKey {

    private String publicKey;

    private String privateKey;
}
