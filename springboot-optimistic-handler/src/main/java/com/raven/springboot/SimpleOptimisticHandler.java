package com.raven.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-12 11:15
 */
@Slf4j
@Service
public class SimpleOptimisticHandler {

    void handler() {
        log.info("1234");
    }
}
