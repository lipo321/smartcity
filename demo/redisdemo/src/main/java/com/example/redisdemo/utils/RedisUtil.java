package com.example.redisdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lipoGiser
 * @date 2019/11/15 9:21
 * @Version 0.1
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire(String key,long time){

        if (time>0){
            redisTemplate.expire(key,time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     * 根据key获取过期时间
     * @param key 不能为null
     * @return 时间(秒) 返回0 代表为永久有效
     */
    public long getExpire(String key) throws Exception {
        if (null == key || key.length()< 1){
            throw new Exception("key值为null");
        }
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true存在 false不存在
     */
    public boolean hasKey(String key) throws Exception {
        if (null == key|| key.length()< 1){
            throw new Exception("key值为null");
        }
        return redisTemplate.hasKey(key);
    }

    /**
     *删除缓存
     * @param key 可以传一个值或者多个
     */
    public void del(String... key){
        if (key != null && key.length>0){
            if (key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }

        }
    }

    //=====================String===================================

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间（秒） time要大于0 如果Time小于等于0 将设置无期限
     * @return true 成功 false失败
     */
    public boolean set(String key,Object value,long time){
        if (time >0){
            redisTemplate.opsForValue().set(key, value, time);
        }else {
            set(key, value);
        }
        return true;
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几（大于0）
     * @return
     */
    public long incr(String key,long delta){
        if (delta<0){
            throw  new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键 不能为null
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key,long delta){
        if (delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //===============================Map==============================

    /**
     * HashGet
     * @param key 键不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key,String item){
        if (null == key ||key.isEmpty()||null==item){
            throw new RuntimeException("数据不合法,key值不能为null，item为null");
        }
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取hashkey对应的所有键值
     * @param key  键
     * @return 对应的多个键值
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true成功 false失败
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hashset 并设置时间
     * @param key  键
     * @param map 对应多个键值
     * @param time 时间（秒）
     * @return true 成功 false失败
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if (time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中存放数据，如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true成功 false失败
     */
     public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
     }


    /**
     * 向一张hash表中存放数据，如果不存在将创建
     * @param key  键
     * @param item 项
     * @param value 值
     * @param time 时间（秒） 注意：如果已存在的hash表有时间，这里将会替换原有的时间
     * @return true 成功 false失败
     */
     public boolean hset(String key,String item,Object value,long time){
         try {
             hset(key, item, value);
             if (time>0){
                 expire(key, time);
             }
             return true;
         }catch (Exception e){
             e.printStackTrace();
             return false;
         }
     }

    /**
     * 删除hash表中的值
     * @param key 值 不能为null
     * @param item 项 可以多个 不能为null
     */
     public void hdel(String key,Object... item){
         redisTemplate.opsForHash().delete(key, item);
     }


    /**
     * 判断hash表中是否有该项的值
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
     public boolean hHasKey(String key,String item){
         return redisTemplate.opsForHash().hasKey(key,item);
     }


    /**
     * hash递增 如果不存在，就会创建一个并把新增后的值返回
     * @param key  键
     * @param item 项
     * @param by 要增加几（大于0）
     * @return
     */
    public double hincr(String key,String item,double by){
         if (by <0.0){
             throw new  RuntimeException("值不能为负数");
         }
         return redisTemplate.opsForHash().increment(key,item,by);
    }

    /**
     * hash 递减
     * @param key 键值
     * @param item 项
     * @param by 要减少（小于零）
     * @return
     */
    public double hdecr(String key,String item,double by){
        if (by < 0.0){
            throw new  RuntimeException("值不能为负数");
        }
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    //==========================set=================================

    public Set<Object> sGet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    public boolean sHasKey(String key,Object value){
        return redisTemplate.opsForSet().isMember(key,value);
    }

    public long sSet(String key,Object... values){
        return redisTemplate.opsForSet().add(key, values);
    }

    public long sSetAndTime(String key,long time,Object... objects){
        Long count  = sSet(key,objects);
        if (time>0){
            expire(key,time);

        }
        return count;
    }

    public long sGetSetSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public long setRemove(String key,Object... values){
        return redisTemplate.opsForSet().remove(key,values);
    }

    //==============================list=========================

    public List<Object> lGet(String key,long start,long end){
        return redisTemplate.opsForList().range(key,start,end);
    }

    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public Object lGetIndex(String key,long index){
        return redisTemplate.opsForList().index(key,index);
    }

    public boolean lSet(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }

    public boolean lSet(String key,Object value,long time){
        if (time>0&&lSet(key, value)){
            expire(key,time);
            return true;
        }
        return false;
    }

    public boolean lSet(String key,List<Object> value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean lSet(String key,List<Object> value,long time){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            if (time>0){
                expire(key,time);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean lUpdateIndex(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long lRemove(String key,long count,Object value){
        try {
            long remove =  redisTemplate.opsForList().remove(key,count,value);
            return remove;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }






}
