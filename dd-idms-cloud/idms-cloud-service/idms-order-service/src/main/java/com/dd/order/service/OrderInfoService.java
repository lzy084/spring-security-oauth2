package com.dd.order.service;

import com.dd.order.dao.OrderInfoMapper;
import com.dd.order.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public OrderInfo get(String id){
        return orderInfoMapper.get(Integer.parseInt(id));
    }


    public List<OrderInfo> findAllList() {
        return orderInfoMapper.findAllList();
    }

    public int insert(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo);
    }

    public int update(OrderInfo orderInfo) {
        return orderInfoMapper.update(orderInfo);
    }

    public int delete(String id) {
        return orderInfoMapper.delete(Integer.parseInt(id));
    }


}
