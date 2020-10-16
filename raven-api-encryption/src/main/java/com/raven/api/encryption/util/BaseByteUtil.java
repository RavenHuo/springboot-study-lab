package com.raven.api.encryption.util;


import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-15 22:59
 */
public class BaseByteUtil {
    /**
     * 字节数组转Base64编码
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**
     * Base64编码转字节数组
     * @param base64Key
     * @return
     * @throws IOException
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Key);
    }
}
