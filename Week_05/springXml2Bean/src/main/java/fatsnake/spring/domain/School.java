package fatsnake.spring.domain;

import fatsnake.spring.interfaces.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {
    
    Klass class1;

    Student student001;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student001);
        
    }
    
}
