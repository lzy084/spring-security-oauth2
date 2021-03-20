package com.dd.cloud.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 账号被锁定
 */
public class AccountLockedException extends AuthenticationException {
    public AccountLockedException(String msg) {
        super(msg);
    }
}
