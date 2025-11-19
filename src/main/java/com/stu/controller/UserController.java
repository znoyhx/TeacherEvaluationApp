package com.stu.controller;


import com.stu.common.constant.JwtClaimsConstant;
import com.stu.common.properties.JwtProperties;
import com.stu.common.result.Result;
import com.stu.common.utils.JwtUtil;
import com.stu.dto.UserDTO;
import com.stu.dto.UserLoginDTO;
import com.stu.entity.User;
import com.stu.service.UserService;
import com.stu.vo.UserLoginVO;
//import io.swagger.annotations.ApiOperation;
import com.stu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags="用户相关接口")
//@Tag(name = "用户相关接口")
public class UserController {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录接口")
//    @Operation(summary = "教师登录接口", description = "传入账号密码，返回 Token")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .nickname(user.getNickname())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }


    /**
     * 创建新用户
     *
     * @param userDTO 用户信息
     * @return 创建成功的用户信息
     */
    @PostMapping
    @ApiOperation("创建新用户")
    public Result<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return Result.success();
    }

    /**
     * 根据昵称查询用户
     *
     * @param nickname 用户昵称
     * @return 用户详情
     */
    @GetMapping
    @ApiOperation("根据昵称查询用户")
    public Result<List<UserVO>> getUserByNickname(@RequestParam String nickname) {
        List<User> users = userService.getByNickname(nickname);
        List<UserVO> userVOS = users.stream()
                .map(user -> UserVO.builder()
                        .id(user.getId())
                        .nickname(user.getNickname())
                        .build()
                )
                .toList();
        return Result.success(userVOS);
    }

    /*
    * 根据id查用户
    *
    * */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询用户")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }

        return Result.success(user);
    }

//    /**
//     * 查询所有用户 (实际项目中应使用分页)
//     *
//     * @return 用户列表
//     */
//    @GetMapping
//    @ApiOperation("查询所有用户")
//    public Result<List<UserVO>> getAllUsers() {
//        List<User> users = userService.list();
//        List<UserVO> userVOs = users.stream().map(this::convertToVO).toList();
//        return Result.success(userVOs);
//    }

    /**
     * 根据 ID 更新用户信息
     *
     * @param id      用户 ID
     * @param userDTO 更新的用户信息
     * @return 更新后的用户信息
     */
    @PutMapping("/{id}")
    @ApiOperation("根据ID更新用户")
    public Result<UserVO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = User.builder()
                .id(id)
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .nickname(userDTO.getNickname())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();

        User updatedUser = userService.updateUser(user);
        UserVO userVO = UserVO.builder()
                .id(updatedUser.getId())
                .nickname(updatedUser.getNickname())
                .build();
        return Result.success(userVO);
    }

//    /**
//     * 根据 ID 删除用户
//     *
//     * @param id 用户 ID
//     * @return 操作结果
//     */
//    @DeleteMapping("/{id}")
//    @ApiOperation("根据ID删除用户")
//    public Result<String> deleteUser(@PathVariable Long id) {
//        boolean success = userService.removeById(id);
//        if (success) {
//            return Result.success("用户删除成功");
//        } else {
//            return Result.error("用户删除失败或用户不存在");
//        }
//    }


}
