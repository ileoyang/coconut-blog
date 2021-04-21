package io.leo.coconut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableScheduling
public class CoconutBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoconutBlogApplication.class, args);
    }

}
