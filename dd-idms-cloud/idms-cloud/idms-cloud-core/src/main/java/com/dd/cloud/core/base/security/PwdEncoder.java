package com.dd.cloud.core.base.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PwdEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return CryptoPasswordEncoder.encryptSha256(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean result = false;
        String originalPassword = CryptoPasswordEncoder.encryptSha256(rawPassword.toString());

        if (originalPassword.equals(encodedPassword)) {
            result = true;
        }
        return result;
    }
}
