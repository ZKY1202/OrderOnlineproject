package com.zkyproject1.onlineorder.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autorities")
public class Authorities implements Serializable{
    // serializable 序列化： 序列化是讲memory 里的格式转化为 bit array 存到磁盘上
    private static final long serialVersionUID =  8734140534986494039L;

    @Id
    private String email;

    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

}


