package com.zecode.z.uperforuser.repositoryPackage.dataHolder;

public class UserSignInData {
    private String email;
    private String password;

    public UserSignInData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
