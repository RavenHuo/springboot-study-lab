package com.raven.version.controller;

import com.raven.version.common.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ApiVersion(4)
@RestController
@RequestMapping("{version}/study")
public class Version4Controller {

    @GetMapping("/happy")
    public String studyHappy() {
        return "happy version4";
    }

    @GetMapping("/diff")
    public String diff() {
        return "version4";
    }
}
