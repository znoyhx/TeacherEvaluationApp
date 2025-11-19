package com.stu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVO {
    private Integer id;

    private String name;

    private String title;

    private String department;

    private String researchArea;
}
