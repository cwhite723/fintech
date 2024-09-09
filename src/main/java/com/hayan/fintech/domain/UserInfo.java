package com.hayan.fintech.domain;

import com.hayan.fintech.common.annotation.Encrypt;
import com.hayan.fintech.domain.base.BaseEntity;
import com.hayan.fintech.domain.dto.CreateUserRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseEntity {

    @Column(unique = true, name = "usr_key")
    private String userKey;

    @Encrypt
    @Column(name = "usr_reg_num")
    private String userRegistrationNumber;

    @Column(name = "usr_nm")
    private String userName;

    @Encrypt
    @Column(name = "usr_icm_amt")
    private String userIncomeAmount;

    public static UserInfo toEntity(CreateUserRequestDto userRequestDto, String userKey) {
        return UserInfo.builder()
                .userIncomeAmount(userRequestDto.userIncomeAmount())
                .userName(userRequestDto.userName())
                .userRegistrationNumber(userRequestDto.userRegistrationNumber())
                .userKey(userKey)
                .build();
    }
}
