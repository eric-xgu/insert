package com.xugu;


import com.xugu.config.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

import java.util.Vector;


/**
 * Hello world!
 *
 */
@SpringBootApplication
//@PropertySource(value={"file:./config/application.yaml"})
public class App  implements CommandLineRunner
{

//    public static void main(String[] args) {
//        SpringApplication.run(App.class,args);
//    }
public static void main(String[] args) {
    new SpringApplicationBuilder().sources(App.class).web(WebApplicationType.NONE).run(args);
}
    @Autowired
    InsertTarget insertTarget;
    @Override
    public void run(String ... args) throws Exception {
        insertTarget.init();
        insertTarget.run();
    }
}
