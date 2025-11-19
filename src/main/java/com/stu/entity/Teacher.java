package com.stu.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer id;

    private String name;

    private String title;

    private String department;

    private String researchArea;

}
