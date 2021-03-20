package com.dd.order.dao;

import com.dd.order.entity.GoodsInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Repository
public interface GoodsInfoMapper{
    GoodsInfo get(int id);

    List<GoodsInfo> findAllList();

    int insert(GoodsInfo entity);

    int insertBatch(List<GoodsInfo> list);

    int update(GoodsInfo entity);

    int delete(int id);

    int queryStoreById(int id);
    int reduceStore(@Param("id") int id, @Param("num") int num);
}