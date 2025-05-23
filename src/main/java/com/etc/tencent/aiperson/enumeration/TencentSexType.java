package com.etc.tencent.aiperson.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TencentSexType {


    MALE("MALE"),
    FEMALE("FEMALE");
    private final String value;
    TencentSexType(final String value) {
        this.value = value;
    }
    @JsonValue
    public String value() {
        return value;
    }

}
