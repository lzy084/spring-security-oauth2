package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysOrg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orgId;
    private Long parentId;
    private String parentIds;
    private String orgName;
    private int orgSort;
    private String area;
    private String orgCode;
    private int orgType;
    private String address;
    private String zipCode;
    private String phone;
    private String fax;
    private String email;
    private Long masterPerson;
    private Long deputyPerson;
    private String remarks;
    private Long tenantId;
    private Long creator;
    private Timestamp createDate;
}