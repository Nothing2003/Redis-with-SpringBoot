package com.api.redis.controllers;

import com.api.redis.dao.UserDao;
import com.api.redis.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDao userDao;
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userDao.update(user);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
      return   userDao.findByUserId(userId);
    }
    @GetMapping
    public List<User> getAllUsers() {
         return userDao.findAll()
                 .values()
                 .stream()
                 .map(value->(User)value)
                 .toList();
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userDao.deleteByUserId(userId);
    }
}
