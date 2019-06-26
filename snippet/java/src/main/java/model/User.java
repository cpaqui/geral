package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private Integer age;
    private BigDecimal money;
    private List<Role> roles = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this(name);
        this.age = age;
    }

    public User(String name, BigDecimal money) {
        this(name);
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
