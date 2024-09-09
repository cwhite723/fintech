package com.hayan.fintech.common.response;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
