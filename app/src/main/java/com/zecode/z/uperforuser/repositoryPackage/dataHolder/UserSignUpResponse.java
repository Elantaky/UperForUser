package com.zecode.z.uperforuser.repositoryPackage.dataHolder;

public class UserSignUpResponse {
    String success,aid;

    public UserSignUpResponse(String success, String aid) {
        this.success = success;
        this.aid = aid;
    }

    public String getSuccess() {
        return success;
    }

    public String getAid() {
        return aid;
    }

}
