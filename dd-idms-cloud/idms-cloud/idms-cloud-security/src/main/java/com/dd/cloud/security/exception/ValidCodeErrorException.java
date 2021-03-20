package com.dd.cloud.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码验证失败异常
 */
public class ValidCodeErrorException extends AuthenticationException {
    public ValidCodeErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidCodeErrorException(String msg) {
        super(msg);
    }
}
