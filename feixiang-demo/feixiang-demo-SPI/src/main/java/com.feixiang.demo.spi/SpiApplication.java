package com.feixiang.demo.spi;


import com.feixiang.demo.spi.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping
public class SpiApplication {

    @Autowired
    private Demo demo;

    public static void main(String[] orgs){
        SpringApplication.run(SpiApplication.class);
    }

    @GetMapping("/test")
    public Object test(){
        return demo.toString();
    }
}
