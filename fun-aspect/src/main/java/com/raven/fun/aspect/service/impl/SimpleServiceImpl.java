package com.raven.fun.aspect.service.impl;

import com.raven.fun.aspect.annontation.SimpleLog;
import com.raven.fun.aspect.service.ISimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 22:20
 */
@Service
@Slf4j
public class SimpleServiceImpl implements ISimpleService {
    @Override
    @SimpleLog(message = "123")
    public void log() {
        log.info("SimpleService   --------------------");
    }
}
