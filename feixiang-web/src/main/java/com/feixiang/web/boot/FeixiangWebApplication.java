package com.feixiang.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.feixiang.web")
public class FeixiangWebApplication {
    public static void main(String[] args){
        SpringApplication.run(FeixiangWebApplication.class,args);
        System.out.println("============FeixiangWebApplication start=============");
    }

}
