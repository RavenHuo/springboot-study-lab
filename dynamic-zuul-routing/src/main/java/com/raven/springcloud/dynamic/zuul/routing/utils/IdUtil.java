package com.raven.springcloud.dynamic.zuul.routing.utils;

import java.util.UUID;

/**
 * @description:
 * @author: huorw
 * @create: 2019-08-31 16:14
 */
public class IdUtil {

    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;

    /** 序列在id中占的位数 */
    private final static long SEQUENCE_BITS = 12L;

    /** 毫秒内序列(0~31) */
    private static long sequence = 0L;

    /** 生成序列的掩码，这里为31  */
    private final static long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    /** 机器ID向左移3位 */
    private final static long WORKER_ID_SHIFT = 12L;

    private final static long WORK_ID = 12L;

    /**
     * 生成32 位uuid  获取
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static Long  get16BitUUId() {
        long timestamp = timeGen();
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        return timestamp << WORKER_ID_SHIFT | (WORK_ID << WORKER_ID_SHIFT)
                | sequence;
    }





    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(get16BitUUId());
    }
}
