package com.raven.version.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/study")
public class VersionController {

    @GetMapping("/happy")
    public String studyHappy() {
        return "happy version1";
    }
}
