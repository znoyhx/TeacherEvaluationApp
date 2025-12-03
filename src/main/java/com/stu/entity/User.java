package com.stu.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phoneNumber;

    public Long getId() { return id; }
    public String getUserName() { return username; }
    public String getPassword() { return password; }
    public String getNickname() { return nickname; }
    public String getPhoneNumber() { return phoneNumber; }
}
