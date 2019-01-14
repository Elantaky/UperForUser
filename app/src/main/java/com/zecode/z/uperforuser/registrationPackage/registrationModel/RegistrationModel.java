package com.zecode.z.uperforuser.registrationPackage.registrationModel;

import com.zecode.z.uperforuser.utilsPackage.AuthManager;

public class RegistrationModel {
    private AuthManager authManager;

    public  RegistrationModel() {
        authManager = new AuthManager();
    }
    public String signInToAuthManager(String userName,String password){
        return authManager.signInToServerNow(userName,password);
    }
    public void signUpToAuthManager(String phoneNumber,String email,String password,String firstName,String lastName){
        authManager.signUpToServerNow(phoneNumber,email,password,firstName,lastName);
    }
}
