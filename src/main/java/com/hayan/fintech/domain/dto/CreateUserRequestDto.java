package com.hayan.fintech.domain.dto;

public record CreateUserRequestDto(String userIncomeAmount,
                                   String userName,
                                   String userRegistrationNumber) {
}
