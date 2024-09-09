package com.hayan.fintech.domain.dto;

import lombok.Builder;

@Builder
public record ProductInfoDto(String organizationCode,
                             String productCode,
                             Double productMaximumInterest,
                             Double productMinimumInterest,
                             String productName) {
}
