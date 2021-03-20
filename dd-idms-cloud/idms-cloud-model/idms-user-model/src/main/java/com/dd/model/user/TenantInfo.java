package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class TenantInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long tenantId;
    private String companyName;
    private String address;
    private String contacts;
    private String tel;
    private String fax;
    private int status;
    private Timestamp expireDate;
    private String words;
    private String domain;
    private String mobile;
    private Long creator;
    private Timestamp createDate;


}