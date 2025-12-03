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
    private Long id;

    private String name;

    private String title;

    private String department;

    private String researchArea;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDepartment() { return department; }
    public String getResearchArea() { return researchArea; }
}
