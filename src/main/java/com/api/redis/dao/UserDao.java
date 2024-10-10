package com.api.redis.dao;

import com.api.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    private  RedisTemplate redisTemplate;
    private static final String KEY="USER";
    public User save(User user) {
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        return user;
    }
    public User findByUserId(String userId) {
        return (User) redisTemplate.opsForHash().get(KEY,userId);
    }
    public Map<Object, Object> findAll() {
        return redisTemplate.opsForHash().entries(KEY);
    }
    public void deleteByUserId(String userId) {
        redisTemplate.opsForHash().delete(KEY,userId);
    }
    public User update(User user) {
      User oldUser=  (User) redisTemplate.opsForHash().get(KEY,user.getUserId());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        redisTemplate.opsForHash().put(KEY,user.getUserId(),oldUser);
        return user;
    }
}
