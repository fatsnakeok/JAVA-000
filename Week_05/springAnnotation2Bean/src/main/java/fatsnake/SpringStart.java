package fatsnake;

import fatsnake.spring.BeanConfig;
import fatsnake.spring.domain.Klass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringStart {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] beanNames = context.getBeanDefinitionNames();
        Klass klass = (Klass) context.getBean("class1");

        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
