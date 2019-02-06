package com.zecode.z.uperforuser.repositoryPackage.dataHolder;

public class GitHubResponse {

    private String login;
    private String avatar_url;

    public GitHubResponse(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
