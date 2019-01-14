package com.zecode.z.uperforuser.utilsPackage;

import com.zecode.z.uperforuser.registrationPackage.registrationInterfaces.RegistrationInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInRetrofitClient {
    private static final String BASE_URL_FOR_SIGN_IN = "http://uber-likeapp.ddns.net:5511/api/signIn/";
    private static SignInRetrofitClient mInstance;
    private Retrofit retrofit;

    private SignInRetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL_FOR_SIGN_IN).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized SignInRetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new SignInRetrofitClient();
        }
        return mInstance;
    }

    public RegistrationInterface.SignInInterface getAPI() {
        return retrofit.create(RegistrationInterface.SignInInterface.class);
    }
}
