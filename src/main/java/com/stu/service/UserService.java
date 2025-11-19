package com.stu.service;

import com.stu.dto.UserDTO;
import com.stu.dto.UserLoginDTO;
import com.stu.entity.User;

import java.util.List;

public interface UserService {
    User login(UserLoginDTO userLoginDTO);

    void createUser(UserDTO userDTO);

    List<User> getByNickname(String nickname);

    User getById(Long id);

    User updateUser(User user);
}
