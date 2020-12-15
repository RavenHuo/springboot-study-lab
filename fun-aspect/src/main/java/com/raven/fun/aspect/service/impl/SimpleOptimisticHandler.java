package com.raven.fun.aspect.service.impl;

import com.raven.fun.aspect.service.ISimpleOptimisticHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-12 11:15
 */
@Slf4j
@Service
public class SimpleOptimisticHandler implements ISimpleOptimisticHandler {

    @Override
    public void handler() {
        log.info("1234");
    }
}
