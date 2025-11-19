package com.stu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glassfish.jaxb.core.v2.TODO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long id;

    private String nickname;
    //TODO: 后面还有该用户的各个评价集合
}
