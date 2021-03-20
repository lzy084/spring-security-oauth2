package com.dd.cloud.security.model.dao;

import com.dd.cloud.security.model.entity.SecurityUserInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityUserInfoMapper{
    SecurityUserInfo findByUserName(@Param("username") String username);
    List<String> queryAuthorities(@Param("userId")Long userId);
}
