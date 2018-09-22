package com.yule.user.entity;

/**
 * 用户实体
 *
 * @author yule
 * @date 2018/8/6 21:51
 */
public class User {
    private String id;
    private String name;
    private String age;

    public User(){

    }
    public User(String name, String age){
        this.name = name;
        this.age = age;
    }
    public User(String id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.age;
    }
}
