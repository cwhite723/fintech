package com.hayan.fintech.domain.constant;

import com.hayan.fintech.common.response.ErrorCode;
import com.hayan.fintech.exception.CustomException;

import java.util.Arrays;
import java.util.Objects;

public interface EnumCode<E> {
    String getCode();

    static <T extends Enum<T> & EnumCode> T ofName(Class<T> enumType, String name) {
        try {
            return Enum.valueOf(enumType, name);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.REQUEST_VALIDATION_FAIL, String.format("%s은(는) 없는 코드입니다.", name));
        }
    }

    static <T extends Enum<T> & EnumCode> T ofCode(Class<T> enumType, String code) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(val -> Objects.equals(val.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.REQUEST_VALIDATION_FAIL, String.format("%s는 없는 코드입니다.", code)));
    }
}
