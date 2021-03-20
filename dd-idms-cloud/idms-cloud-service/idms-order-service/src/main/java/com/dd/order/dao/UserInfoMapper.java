package com.dd.order.dao;

import com.dd.order.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Repository
public interface UserInfoMapper{
    UserInfo get(int id);

    List<UserInfo> findAllList();

    int insert(UserInfo entity);

    int insertBatch(List<UserInfo> list);

    int update(UserInfo entity);

    int delete(int id);
}