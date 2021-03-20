package com.dd.oauth.server.principal.dao;

import com.dd.cloud.core.base.mapper.IBaseMapper;
import com.dd.oauth.server.principal.entity.SecurityUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-05-20
******************************************************************/
@Repository
public interface SecurityUserInfoMapper extends IBaseMapper<SecurityUserInfo> {
    SecurityUserInfo findByUserName(@Param("username") String username);
    List<String> queryAuthorities(@Param("userId")Long userId);

}