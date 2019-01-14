package com.zecode.z.uperforuser.repositoryPackage.dataHolder;

public class UserSignInResponse {
    String email,firstName,phoneNo,lastName,success,aid;

    public UserSignInResponse(String email, String firstName, String phoneNo, String lastName, String success, String aid) {
        this.email = email;
        this.firstName = firstName;
        this.phoneNo = phoneNo;
        this.lastName = lastName;
        this.success = success;
        this.aid = aid;
    }

    public String getEmail() {
        return email;
    }



    public String getFirstName() {
        return firstName;
    }



    public String getPhoneNo() {
        return phoneNo;
    }



    public String getLastName() {
        return lastName;
    }



    public String getSuccess() {
        return success;
    }


    public String getAid() {
        return aid;
    }

}
