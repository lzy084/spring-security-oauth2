package com.dd.user.dao;

import com.dd.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.dd.commons.mybatis.IBaseMapper;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Repository
public interface UserInfoDao extends IBaseMapper<UserInfo>{
    UserInfo findByUserName(@Param("userName") String userName);
}