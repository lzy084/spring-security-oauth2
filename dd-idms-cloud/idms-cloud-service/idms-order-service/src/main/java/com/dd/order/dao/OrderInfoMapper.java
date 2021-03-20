package com.dd.order.dao;

import com.dd.order.entity.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Repository
public interface OrderInfoMapper{
    OrderInfo get(int id);

    List<OrderInfo> findAllList();

    int insert(OrderInfo entity);

    int insertBatch(List<OrderInfo> list);

    int update(OrderInfo entity);

    int delete(int id);

}