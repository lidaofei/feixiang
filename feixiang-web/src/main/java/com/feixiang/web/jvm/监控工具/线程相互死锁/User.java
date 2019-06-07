package com.feixiang.web.jvm.监控工具.线程相互死锁;

/**
 * @Author: lidaofei
 * @Date: 2019/6/7 16:55
 * @Description:
 */
public class User {
    private Long id ;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
