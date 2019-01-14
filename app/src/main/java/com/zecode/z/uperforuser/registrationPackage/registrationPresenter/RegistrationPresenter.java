package com.zecode.z.uperforuser.registrationPackage.registrationPresenter;


import android.app.Activity;
import android.app.Activity.*;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Patterns;
import android.widget.Toast;

import com.zecode.z.uperforuser.R;
import com.zecode.z.uperforuser.mapPackage.mapView.MapsActivity;
import com.zecode.z.uperforuser.registrationPackage.registrationInterfaces.RegistrationInterface;
import com.zecode.z.uperforuser.registrationPackage.registrationModel.RegistrationModel;
import com.zecode.z.uperforuser.registrationPackage.registrationView.RegistrationActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class RegistrationPresenter implements RegistrationInterface.presenter {

    RegistrationInterface.View view;
    RegistrationModel model;
    Context context;

    public RegistrationPresenter(RegistrationInterface.View view, Context context) {
        String email, password;
        DialogInterface dialog;
        model = new RegistrationModel();
        this.view = view;
        this.context = context;
    }

    @Override
    public void signInClicked() {
        view.showSignInDialog();
    }

    @Override
    public void registerClicked() {
        view.showRegisterDialog();
    }

    @Override
    public String dialogSignInClicked(Context context, Activity activity, DialogInterface dialog, String email, String password) {
        email = email;
        password = password;
        dialog = dialog;
        if (android.text.TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid email");
            return null;
        }
        if (password.length() < 6) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid password");
            return null;
        }


        view.hideDialog(dialog);
        return model.signInToAuthManager(email, password);
    }

    @Override
    public void dialogRegisterClicked(DialogInterface dialog, String phoneNumber, String email, String password, String firstName, String lastName) {
        if (android.text.TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid email");
            return;
        }
        if (password.length() < 6) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid password");
            return;
        }
        model.signUpToAuthManager(phoneNumber, email, password, firstName, lastName);
        view.hideDialog(dialog);
    }

    @Override
    public void dialogCancelClicked(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    public void nowLocationStateShallBeChecked(final Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("gps network not enabled");
            dialog.setPositiveButton("open location settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                }
            });
            dialog.show();
        }
    }

    @Override
    public void nowNetworkStateShallBeChecked(final Activity activity) {
        if (!checkNetworkStatus()) {

            view.showInternetConnectionDialog();
        }
    }

    @Override
    public boolean checkNetworkStatus() {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;

    }



}
