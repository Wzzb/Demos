package com.test.litepaldemo.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by H235952 on 2018/3/23.
 */

public class Developer extends DataSupport {
    @Column(nullable = false, unique = true, defaultValue = "unknown")
    private String name;

    private int age;

    private Date birthday;

    @Column(ignore = true)
    private float income;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }
}
