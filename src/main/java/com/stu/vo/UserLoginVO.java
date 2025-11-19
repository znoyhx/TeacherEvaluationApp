package com.stu.vo;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学生登录返回的数据格式")
public class UserLoginVO {

    @Schema(description = "主键值")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "jwt令牌")
    private String token;
}

