package com.dd.cloud.core.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenEntity implements Serializable {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String expiresIn;
    private String scope;
}
