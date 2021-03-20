package com.dd.order.entity;

import lombok.Data;

import java.io.Serializable;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Data
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int goodsId;
    private String goodsName;
    private int goodsNum;


}