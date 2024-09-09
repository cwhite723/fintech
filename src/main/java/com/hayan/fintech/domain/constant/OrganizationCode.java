package com.hayan.fintech.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationCode implements EnumCode{
    NONE("00000"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002");

    private final String code;

    public static OrganizationCode ofName(String name) {
        return EnumCode.ofName(OrganizationCode.class, name);
    }

    public static OrganizationCode ofCode(String code) {
        return EnumCode.ofCode(OrganizationCode.class, code);
    }
}
