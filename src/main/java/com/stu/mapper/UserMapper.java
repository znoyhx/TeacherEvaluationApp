package com.stu.mapper;

import com.stu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface UserMapper {

    User getByUsername(String username);

    void insert(User user);

    List<User> getByNickname(String nickname);

    User getById(Long id);


    void update(User user);
}
