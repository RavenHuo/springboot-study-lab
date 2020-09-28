package com.raven.version.controller;

import com.raven.version.common.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ApiVersion(8)
@RestController
@RequestMapping("{version}/study")
public class OtherVersion8Controller {

    @Autowired
    private VersionController versionController;

    @GetMapping("/unhappy")
    public String studyUnhappy() {
        return "version-unhappy";
    }

    @GetMapping("/happyAndUnhappy")
    public String studyHappy() {
        return "version-unhappy" + versionController.studyHappy();
    }
}
