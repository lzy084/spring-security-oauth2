package com.dd.cloud.security.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecurityResource implements Serializable {
    String action;
    String permission;
}
