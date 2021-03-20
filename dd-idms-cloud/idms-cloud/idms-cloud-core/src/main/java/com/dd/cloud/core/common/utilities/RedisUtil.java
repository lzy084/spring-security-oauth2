package com.dd.cloud.core.common.utilities;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class RedisUtil {
    @Resource
    public RedisTemplate<Object, Object> redisTemplate;
    /**
     * 默认过期时间
     */
    private static final long DEFAULT_EXPIRE = 7200;

    /**
     * 根据key 删除 对象
     *
     * @param key key
     * @return 删除成功返回true, 否则返回false
     */
    public Boolean delByKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除key
     *
     * @param keys key
     * @return Boolean
     */
    public Boolean delByKeys(String... keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 判断key 是否存在
     *
     * @param key key
     * @return Boolean
     */
    public Boolean existKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 移除指定key 的过期时间
     *
     * @param key key
     * @return 成功 true 失败false
     */
    public Boolean removeExpireByKey(String key) {
        return redisTemplate.boundValueOps(key).persist();
    }

    /**
     * 设置 key 的过期时间
     *
     * @param key  key
     * @param time 时间 秒
     * @return 成功返回true ,否则返回false
     */
    public Boolean setExpireByKey(String key, long time) {
        return redisTemplate.expire(key, time, SECONDS);
    }

    /**
     * 根据key获取值
     *
     * @param key 键
     * @return 值
     */
    public Object strGet(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    /**
     * 将值放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void strSet(String key,Object value){
        strSet(key,value,DEFAULT_EXPIRE);
    }
    /**
     * 将值放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time 过期时间
     */
    public void strSet(String key,Object value,long time){
        if(time>0){
            redisTemplate.opsForValue().set(key,value,time,SECONDS);
        }
        else{
            redisTemplate.opsForValue().set(key,DEFAULT_EXPIRE,time,SECONDS);
        }
    }
    /**
     * 批量添加 key (重复的键会覆盖)
     *
     * @param keyAndValue key value
     */
    public void strBatchSet(Map<String, Object> keyAndValue) {
        redisTemplate.opsForValue().multiSet(keyAndValue);
    }
    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue key value 键值对
     */
    public void strBatchSetIfAbsent(Map<String, String> keyAndValue) {
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }
    public void setIfAbsent(String key,Object value){
        setIfAbsent(key,value,DEFAULT_EXPIRE);
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值。
     * @param key key
     * @param value value
     * @param time 过期时间
     */
    public void setIfAbsent(String key,Object value,Long time){
       if(time!=null && time.intValue()>0){
           redisTemplate.opsForValue().setIfAbsent(key,value,time,SECONDS);
       }
       else{
           redisTemplate.opsForValue().setIfAbsent(key,value);
       }
    }

    /**
     * 返回所有(一个或多个)给定 key 的值。
     * 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。
     * @param keys key集合
     * @return 对象
     */
    public  List<Object> strBatchGet(List<Object> keys){
      return redisTemplate.opsForValue().multiGet(keys);
    }
    public void strAppend(String key,String value){
        redisTemplate.opsForValue().append(key,value);
    }

    /**
     * 用于获取存储在指定 key 中字符串的子字符串。
     * 字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * @param key key
     * @param start 开始
     * @param end 结束
     * @return  子字符串
     */
    public Object substring(String key,long start,long end){
        if(start>end){
           return null;
        }
       return redisTemplate.opsForValue().get(key,start,end);
    }

    /**
     * 用于设置指定 key 的值，并返回 key 的旧值。
     * @param key key
     * @param value 设定的值
     */
    public void strGetAndSet(String key,Object value){
        redisTemplate.opsForValue().getAndSet(key,value);
    }
    public Double increment(String key,double delta){
      return  redisTemplate.opsForValue().increment(key,delta);
    }
    public Long increment(String key,long delta){
        return  redisTemplate.opsForValue().increment(key,delta);
    }


}