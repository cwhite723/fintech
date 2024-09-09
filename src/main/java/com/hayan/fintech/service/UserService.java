package com.hayan.fintech.service;

import com.hayan.fintech.domain.dto.CreateUserRequestDto;
import com.hayan.fintech.domain.dto.CreateUserResponseDto;
import com.hayan.fintech.domain.dto.GetUserResponseDto;

public interface UserService {
    CreateUserResponseDto save(CreateUserRequestDto createUserRequestDto);
    GetUserResponseDto loadUserInfo(String userKey);
}
