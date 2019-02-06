package com.zecode.z.uperforuser.registrationPackage.registrationInterfaces;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.zecode.z.uperforuser.repositoryPackage.dataHolder.GitHubResponse;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.PostToPlaceHolderResponse;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.UserSignInResponse;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.UserSignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistrationInterface {

    interface presenter {

        void signInClicked();

        void registerClicked();

        String dialogSignInClicked(Context context, Activity activity, DialogInterface dialog, String email, String password);

        void dialogRegisterClicked(DialogInterface dialog, String phoneNumber, String email, String password, String firstName, String lastName);

        void dialogCancelClicked(DialogInterface dialog);

        void nowLocationStateShallBeChecked(Context context);

        void nowNetworkStateShallBeChecked(Activity activity);

        boolean checkNetworkStatus();
    }

    interface View {

        void showSignInDialog();

        void showRegisterDialog();

        void hideDialog(DialogInterface dialog);

        void showSnackBar(String message);

        // boolean checkNetworkStatus();
        void showInternetConnectionDialog();


        //void showToast(String message);

    }

    interface ModelCallbacks {

        // void onSignInSuccess();

        //void onRegisterSuccess(String driverId, String email, String password, String userName, String phoneNumber);

        //void onSignInFailure(String failureMessage);

        //void onRegisterFailure(String failureMessage);

        //void onSaveDriverSuccess(String userName);

        //void onSaveDriverFailure();
    }

    interface PresenterCallbacks {

        //void onSignInSuccess();

        // void onRegisterSuccess(String driverId, String email, String password, String userName, String phoneNumber);

        // void onSignInFailure(String failureMessage);

        //  void onRegisterFailure(String failureMessage);

        //  void onSaveDriverSuccess(String userName);

        // void onSaveDriverFailure();
    }

    interface SignUpInterface {

        @FormUrlEncoded
        @POST("{signUp}")
        Call<UserSignUpResponse> signUp(

                @Field("phoneNo") String phoneNo,
                @Field("email") String email,
                @Field("password") String password,
                @Field("firstName") String firstName,
                @Field("lastName") String lastName
        );
    }

    interface SignInInterface {
        @FormUrlEncoded
        @POST("{signIn}")
        Call<UserSignInResponse> signIn(
                @Field("email") String email,
                @Field("password") String password
        );
    }

    interface GitHupInterface {
        @FormUrlEncoded
        @POST("posts")
        Call<PostToPlaceHolderResponse> getGitHubAccountData(
                @Field("userId") String userId,
                @Field("id") String id,
                @Field("title") String title,
                @Field("body") String body
        );
    }

}
