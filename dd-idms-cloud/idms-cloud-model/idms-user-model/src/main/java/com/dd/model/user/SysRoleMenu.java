package com.dd.model.user;

import lombok.Data;

import java.io.Serializable;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;
    private Long menuId;


}