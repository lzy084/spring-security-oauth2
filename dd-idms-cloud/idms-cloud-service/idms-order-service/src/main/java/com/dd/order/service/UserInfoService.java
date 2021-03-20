package com.dd.order.service;

import com.dd.order.dao.UserInfoMapper;
import com.dd.order.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-09-21
******************************************************************/
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo get(String id){
        return userInfoMapper.get(Integer.parseInt(id));
    }


    public List<UserInfo> findAllList() {
        return userInfoMapper.findAllList();
    }

    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    public int update(UserInfo userInfo) {
        return userInfoMapper.update(userInfo);
    }

    public int delete(String id) {
        return userInfoMapper.delete(Integer.parseInt(id));
    }

}
