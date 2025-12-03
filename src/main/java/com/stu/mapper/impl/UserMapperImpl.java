package com.stu.mapper.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.stu.entity.User;
import com.stu.mapper.UserMapper;

public class UserMapperImpl extends Database implements UserMapper {
    public UserMapperImpl() {
        super();
    }

    public void insert(User user) {
        try {
            String insertSql = "INSERT INTO student (id, username, password, nickname, phonenumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getNickname());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        String updateSql = "UPDATE teacher SET username = ?, password = ?, nickname = ?, phonenumber = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getPhoneNumber());
            ps.setLong(5, user.getId());

            int rows = ps.executeUpdate(); // 返回受影响的行数
            if (rows <= 0) {
                throw new IllegalArgumentException("No such student");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getById(Long id) {
        User ret = null;
        try {
            // ===== 查询数据 =====
            String sql = "SELECT id, username, password, nickname, phonenumber FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (id == rs.getLong("id")) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String nickname = rs.getString("nickname");
                    String phonenumber = rs.getString("phonenumber");
                    ret = new User(id, username, password, nickname, phonenumber);
                }
            }

            if (ret == null) {
                throw new IllegalArgumentException("No such student");
            }
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public User getByUsername(String username) {
        User ret = null;
        try {
            // ===== 查询数据 =====
            String sql = "SELECT id, username, password, nickname, phonenumber FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    Long id = rs.getLong("id");
                    String password = rs.getString("password");
                    String nickname = rs.getString("nickname");
                    String phonenumber = rs.getString("phonenumber");
                    ret = new User(id, username, password, nickname, phonenumber);
                }
            }

            if (ret == null) {
                throw new IllegalArgumentException("No such student");
            }
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<User> getByNickname(String nickname) {
        List<User> ret = new ArrayList<>();
        try {
            // ===== 查询数据 =====
            String sql = "SELECT id, username, password, nickname, phonenumber FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (nickname == rs.getString("nickname")) {
                    Long id = rs.getLong("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String phonenumber = rs.getString("phonenumber");
                    User data = new User(id, username, password, nickname, phonenumber);
                    ret.add(data);
                }
            }

            if (ret.isEmpty()) {
                throw new IllegalArgumentException("No such student");
            }
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
