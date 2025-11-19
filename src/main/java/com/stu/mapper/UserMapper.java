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

    @Select("select * from userdata where username = #{username}")
    User getByUsername(String username);

    @Insert("insert into userdata (username, password, nickname, phone_number) values (#{username}, #{password}, #{nickname}, #{phoneNumber})")
    void insert(User user);

    @Select("select * from userdata where nickname like CONCAT('%', #{nickname}, '%')")
    List<User> getByNickname(String nickname);

    @Select("select * from userdata where id = #{id}")
    User getById(Long id);


    void update(User user);
}
