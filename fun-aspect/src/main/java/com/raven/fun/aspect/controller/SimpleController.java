package com.raven.fun.aspect.controller;

import com.raven.fun.aspect.service.ISimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 22:22
 */
@RestController
@Slf4j
public class SimpleController {

    @Autowired
    private ISimpleService simpleService;

    @GetMapping("/log")
    public void log() {
        simpleService.log();
    }

    @GetMapping("/optimisticHandler")
    public void optimisticHandler() {
        simpleService.optimisticHandler();
    }
}
