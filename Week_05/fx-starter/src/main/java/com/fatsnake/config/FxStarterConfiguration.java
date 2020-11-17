package com.fatsnake.config;

import com.fatsnake.domain.Klass;
import com.fatsnake.domain.School;
import com.fatsnake.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/18 06:42
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
@Configuration
public class FxStarterConfiguration {


    @Bean
    public Student student100() {
        return new Student().create();
    }

    @Bean
    public Klass klass() {
        Klass k = new Klass();
        List<Student> studentList = new ArrayList<>();
        studentList.add(student100());
        k.setStudents(studentList);
        return k;
    }


    @Bean
    public School school() {
        School school = new School();
        school.setClass1(klass());
        school.setStudent100(student100());
        return school;
    }

}
