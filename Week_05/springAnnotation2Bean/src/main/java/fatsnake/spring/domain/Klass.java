package fatsnake.spring.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class Klass {
    
    List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
