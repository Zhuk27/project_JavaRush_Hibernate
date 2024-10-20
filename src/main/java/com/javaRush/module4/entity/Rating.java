package com.javaRush.module4.entity;

import lombok.Getter;

@Getter
public enum Rating {
    G("g"),
    PG("pg"),
    PG_13("pg-13"),
    R("r"),
    NC_17("nc-17"),
    ;
    private final String value;

    Rating(String s) {
        this.value = s;
    }
}
