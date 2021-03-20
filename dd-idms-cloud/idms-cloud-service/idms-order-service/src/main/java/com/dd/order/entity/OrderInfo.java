package com.dd.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Data
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int goodsId;
    private int userId;
    private Timestamp createTime;
    private int goodsUnm;


}