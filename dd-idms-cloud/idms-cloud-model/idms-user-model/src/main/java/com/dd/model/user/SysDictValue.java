package com.dd.model.user;

import java.io.Serializable;

import lombok.Data;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Data
public class SysDictValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long typeId;
    private String label;
    private String value;
    private int sort;

}