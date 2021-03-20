package com.dd.cloud.security.exception;

import org.springframework.security.access.AccessDeniedException;

public class CloudAccessDeniedException  extends AccessDeniedException {
    public CloudAccessDeniedException(String msg) {
        super(msg);
    }
}

