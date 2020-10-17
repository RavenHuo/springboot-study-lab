package com.raven.api.encryption.data;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-17 16:18
 */
@Data
public class EncryptionData {

    @NotNull(message = "value is not null")
    private String value;
}
