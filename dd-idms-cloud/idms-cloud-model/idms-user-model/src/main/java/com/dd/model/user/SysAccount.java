package com.dd.model.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long accountId;
    private Long userId;
    private int accountType;
    private String accountName;
    private String accountPwd;
    private Long tenantId;
    private int status;
    private int loginCount;
    private int locked;
    private String loginIp;
    private Timestamp lastLogin;
    private String domain;
    private Long creator;
    private Timestamp createDate;
    private SysUser sysUser;


}