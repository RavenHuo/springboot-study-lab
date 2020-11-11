package com.raven.springcloud.dynamic.zuul.routing.enums;

import lombok.Getter;

@Getter
public enum InitEnums {

    INIT("INIT");

    public String value;

    InitEnums(String value) {
        this.value = value;
    }
}
