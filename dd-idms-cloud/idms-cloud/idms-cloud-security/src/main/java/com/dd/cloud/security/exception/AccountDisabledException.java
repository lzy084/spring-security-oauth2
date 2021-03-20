package com.dd.cloud.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户被禁用
 */
public class AccountDisabledException extends AuthenticationException {
    public AccountDisabledException(String msg, Throwable t) {
        super(msg, t);
    }

    public AccountDisabledException(String msg) {
        super(msg);
    }
}
