package com.hayan.fintech.util.converter;

import com.hayan.fintech.domain.constant.OrganizationCode;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationCodeConverter extends AbstractEnumAttributeConverter<OrganizationCode, String> {

    protected OrganizationCodeConverter() {
        super(OrganizationCode.class);
    }
}
