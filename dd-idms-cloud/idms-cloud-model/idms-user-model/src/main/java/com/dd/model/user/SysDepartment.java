package com.dd.model.user;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysDepartment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long departmentId;
    private Long orgId;
    private String departmentName;
    private Long parentId;
    private String parentIds;
    private BigDecimal departmentSort;
    private Long areaId;
    private String departmentCode;
    private int departmentType;
    private int grade;
    private String address;
    private String zipCode;
    private String phone;
    private String fax;
    private String email;
    private Boolean enable;
    private Long primaryPerson;
    private Long deputyPerson;
    private String remarks;
    private int delFlag;
    private Long creator;
    private Timestamp createDate;


}