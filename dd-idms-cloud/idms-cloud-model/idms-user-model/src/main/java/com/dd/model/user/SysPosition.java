package com.dd.model.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long positionId;
    private String positionName;
    private Long orgId;
    private String remarks;
    private Long tenantId;
    private Long creator;
    private Timestamp createDate;


}