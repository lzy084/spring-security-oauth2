package com.dd.cloud.core.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuzy
 * @title: SysRateLimiter
 * @projectName idms-cloud
 * @description: TODO 限流配置表
 * @date 2020/5/4 19:40
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateLimiter implements Serializable {

    private int limitId;

    /**
     * 等极
     **/
    private String level;

    /**
     * 等极名称（描述）
     **/
    private String levelName;

    /**
     * 流速
     **/
    private int replenishRate;

    /**
     * 桶容量
     **/
    private int burstCapacity;

    /**
     * 单位 1:秒，2:分钟，3:小时，4:天
     **/
    private int limitType;

}
