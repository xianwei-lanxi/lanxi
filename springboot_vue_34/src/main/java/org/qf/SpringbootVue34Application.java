package org.qf;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("org.qf.mapper")
@SpringBootApplication
public class SpringbootVue34Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVue34Application.class, args);
    }

}
