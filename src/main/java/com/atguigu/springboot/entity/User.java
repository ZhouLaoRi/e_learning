package com.atguigu.springboot.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer userLevel;

    private String userDirection;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserDirection() {
        return userDirection;
    }

    public void setUserDirection(String userDirection) {
        this.userDirection = userDirection == null ? null : userDirection.trim();
    }
}