package fatsnake.spring;

import fatsnake.spring.domain.Klass;
import fatsnake.spring.domain.School;
import fatsnake.spring.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/18 20:57
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
@Configuration
public class BeanConfig {

    @Bean
    public Klass class1() {
        Klass klass = new Klass();
        klass.setStudents(new ArrayList<>());
        klass.getStudents().add(student100());
        return klass;
    }

    @Bean
    public School school() {
        return new School();
    }

    @Bean
    public Student student100() {
        return Student.create();
    }

}
