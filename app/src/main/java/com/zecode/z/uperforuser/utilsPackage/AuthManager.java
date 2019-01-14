package com.zecode.z.uperforuser.utilsPackage;


import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.zecode.z.uperforuser.registrationPackage.registrationPresenter.RegistrationPresenter;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.UserSignInData;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.UserSignInResponse;
import com.zecode.z.uperforuser.repositoryPackage.dataHolder.UserSignUpResponse;

import java.net.URISyntaxException;

public class AuthManager {

    public String accountId;

/*
    String emilForSignIn;
    String passwordForSignIn;
    UserSignInData userSignInData;
*/


/*    public AuthManager() {
    }*/

    /*public AuthManager(String emilForSignIn, String passwordForSignIn) {
        this.emilForSignIn = emilForSignIn;
        this.passwordForSignIn = passwordForSignIn;
        userSignInData =new UserSignInData(emilForSignIn,passwordForSignIn);
    }*/

    ///////////// for retrofit
    public void signUpToServerNow(String phoneNumber, String email, String password, String firstName, String lastName) {
        retrofit2.Call<UserSignUpResponse> signInCall = SignUpRetrofitClient.getInstance().getAPI().signUp(phoneNumber, email, password, firstName, lastName);
        signInCall.enqueue(new Callback<UserSignUpResponse>() {
            @Override
            public void onResponse(Call<UserSignUpResponse> call, Response<UserSignUpResponse> response) {
                UserSignUpResponse userSignUpResponse = response.body();
                Log.v("zooo ", " sing up response tmam " + userSignUpResponse.getAid());
            }

            @Override
            public void onFailure(Call<UserSignUpResponse> call, Throwable t) {
                Log.v("zooo", "sign up response not tmam ");
            }
        });
    }
    public String signInToServerNow(String userName, String password) {
        retrofit2.Call<UserSignInResponse> signInCall = SignInRetrofitClient.getInstance().getAPI().signIn(userName, password);
        signInCall.enqueue(new Callback<UserSignInResponse>() {
            @Override
            public void onResponse(Call<UserSignInResponse> call, Response<UserSignInResponse> response) {
                UserSignInResponse userSignInResponse = response.body();
                Log.v("zooo", "sign in response tmam " + userSignInResponse.getFirstName());
                accountId = userSignInResponse.getAid();
            }
            @Override
            public void onFailure(Call<UserSignInResponse> call, Throwable t) {
                Log.v("zooo", "sing in response not tmam ");
            }
        });
        return accountId;
    }
/*    class WebThread extends AsyncTask<UserSignInData, String, UserSignInResponse> {
UserSignInData userSignInData ;
String name;
String password;

        public WebThread(UserSignInData userSignInData, String name, String password) {
            this.userSignInData = userSignInData;
            this.name = name;
            this.password = password;
        }

        public WebThread(UserSignInData userSignInData) {
            this.userSignInData = userSignInData;
        }

        @Override
        protected UserSignInResponse doInBackground(UserSignInData... userSignInData) {
            signInToServerNow(userSignInData)
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(UserSignInResponse userSignInResponse) {
            super.onPostExecute(userSignInResponse);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
*/

}
