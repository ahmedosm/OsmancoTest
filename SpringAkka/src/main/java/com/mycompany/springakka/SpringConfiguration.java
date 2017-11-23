package com.mycompany.springakka;

import kamon.Kamon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = "com.mycompany.springakka")
 
public class SpringConfiguration {

  //  public static void main(String[] args) {
        //SpringApplication.run(new Object[]{SpringConfiguration.class,AuthorizationServerConfiguration.class}, args);
//        SpringApplication app = new SpringApplication(SpringConfiguration.class);
//        app.setHeadless(false);
//        app.setWebEnvironment(true);
//        app.run(args);
////        Kamon.start();
////       SpringApplication.run(KamonController.class, args);

 //   }
    
//    @Import(JpaConfiguration.class)
//@SpringBootApplication(scanBasePackages={"com.websystique.springboot"})
//public class SpringBootCRUDApp {
// 
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootCRUDApp.class, args);
//    }
//}

}
