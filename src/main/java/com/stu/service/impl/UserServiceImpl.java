package com.stu.service.impl;

import com.stu.dto.UserDTO;
import com.stu.dto.UserLoginDTO;
import com.stu.entity.User;
import com.stu.mapper.UserMapper;
import com.stu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        User user = userMapper.getByUsername(username);
        return user;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = new User();
        if (userMapper.getByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        if(userMapper.getByNickname(userDTO.getNickname()) != null) {
            throw new IllegalArgumentException("昵称已存在");
        }
        user.setNickname(userDTO.getNickname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userMapper.insert(user);
    }

    @Override
    public List<User> getByNickname(String nickname) {
        return userMapper.getByNickname(nickname);
    }

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User updateUser(User user) {
        userMapper.update(user);
        return user;
    }


}