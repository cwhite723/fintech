package com.hayan.fintech.service.impl;

import com.hayan.fintech.common.response.ErrorCode;
import com.hayan.fintech.domain.UserInfo;
import com.hayan.fintech.domain.dto.CreateUserRequestDto;
import com.hayan.fintech.domain.dto.CreateUserResponseDto;
import com.hayan.fintech.domain.dto.GetUserResponseDto;
import com.hayan.fintech.exception.CustomException;
import com.hayan.fintech.repository.UserInfoRepository;
import com.hayan.fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public CreateUserResponseDto save(CreateUserRequestDto userRequestDto) {
        existsByRegistrationNumber(userRequestDto.userRegistrationNumber());
        String userKey = generateKey();
        userInfoRepository.save(UserInfo.toEntity(userRequestDto, userKey));

        return new CreateUserResponseDto(userKey);
    }

    @Override
    public GetUserResponseDto loadUserInfo(String userKey) {
        var userInfo = getByUserKey(userKey);

        return new GetUserResponseDto(userKey, userInfo.getUserRegistrationNumber());
    }

    private String generateKey() {
        String userKey = UUID.randomUUID().toString();;

        while (userInfoRepository.existsByUserKey(userKey)) {
            userKey = UUID.randomUUID().toString();
        }

        return userKey;
    }

    private void existsByRegistrationNumber(String userRegistrationNumber) {
        if (userInfoRepository.existsByUserRegistrationNumber(userRegistrationNumber)) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private UserInfo getByUserKey(String userKey) {
        return userInfoRepository.findByUserKey(userKey)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
