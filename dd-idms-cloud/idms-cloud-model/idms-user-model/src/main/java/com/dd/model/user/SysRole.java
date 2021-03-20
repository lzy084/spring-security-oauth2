package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;
    private Long orgId;
    private String roleName;
    private String roleCode;
    private int roleType;
    private String remarks;
    private Long tenantId;
    private Long creator;
    private Timestamp createDate;


}