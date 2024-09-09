package com.hayan.fintech.util.converter;

import com.hayan.fintech.common.response.ErrorCode;
import com.hayan.fintech.domain.constant.EnumCode;
import com.hayan.fintech.exception.CustomException;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractEnumAttributeConverter<E extends Enum<E> & EnumCode, T> implements AttributeConverter<E, T> {

    private final Class<E> enumClass;

    protected AbstractEnumAttributeConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        return (T) attribute.name();
    }

    @Override
    public E convertToEntityAttribute(T dbData) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(val -> Objects.equals(val.name(), dbData))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.REQUEST_VALIDATION_FAIL, String.format("%s는 없는 코드입니다.", dbData)));
    }
}

