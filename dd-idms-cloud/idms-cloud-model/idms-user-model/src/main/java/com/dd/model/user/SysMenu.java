package com.dd.model.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long menuId;
    private Long parentId;
    private String parentIds;
    private String menuName;
    private int menuSort;
    private int menuType;
    private String permission;
    private String icon;
    private String action;
    private String path;
    private String target;
    private String remarks;
    private int status;
    private Long tenantId;
    private int registerFlag;
    private Long creator;
    private Timestamp createDate;
    private List<SysMenu> child;


}