package com.example.dongh.grmr.Constructor;

public class UserInformation {
    private final String nickName;
    private final String password;
    private final String email;

    public UserInformation(String email, String password, String nickName ) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }
}