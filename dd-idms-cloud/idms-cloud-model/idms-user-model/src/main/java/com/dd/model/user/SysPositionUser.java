package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysPositionUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long positionId;
    private Long userId;


}