package com.hayan.fintech.controller;

import com.hayan.fintech.common.response.ApplicationResponse;
import com.hayan.fintech.common.response.SuccessCode;
import com.hayan.fintech.domain.dto.CreateUserRequestDto;
import com.hayan.fintech.domain.dto.CreateUserResponseDto;
import com.hayan.fintech.domain.dto.GetUserResponseDto;
import com.hayan.fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fintech/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/information")
    public ApplicationResponse<CreateUserResponseDto> create(@RequestBody CreateUserRequestDto userInfoDto) {
        var savedUserInfo = userService.save(userInfoDto);

        return ApplicationResponse.ok(savedUserInfo, SuccessCode.SUCCESS);
    }

    @GetMapping("/private-info/{userKey}")
    public ApplicationResponse<GetUserResponseDto> getInfo(@PathVariable("userKey") String userKey) {
        var foundUserInfo = userService.loadUserInfo(userKey);

        return ApplicationResponse.ok(foundUserInfo, SuccessCode.SUCCESS);
    }
}
