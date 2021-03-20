package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userNo;
    private String userName;
    private String email;
    private String phone;
    private String photo;
    private String remarks;
    private String currentAddress;
    private String residentAddress;
    private String nation;
    private String politicalStatus;
    private String graduateInstitutions;
    private Timestamp birthday;
    private String cardNo;
    private int marriageStatus;
    private Timestamp entryDate;
    private Timestamp dimissionDate;
    private String education;
    private int gender;
    private String nativePlace;
    private String duty;
    private Long positionId;
    private String openBank;
    private String openBankAddress;
    private String openBankNumber;
    private int deleted;
    private Long tenantId;
    private String professionalRanks;
    private int userType;
    private int residentType;
    private Timestamp lastDimissionDate;
    private String clientId;
    private int sort;
    private Long creator;
    private Timestamp createDate;


}