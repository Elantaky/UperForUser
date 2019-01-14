package com.zecode.z.uperforuser.registrationPackage.registrationView;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zecode.z.uperforuser.R;
import com.zecode.z.uperforuser.mapPackage.mapView.MapsActivity;
import com.zecode.z.uperforuser.registrationPackage.registrationInterfaces.RegistrationInterface;
import com.zecode.z.uperforuser.registrationPackage.registrationPresenter.RegistrationPresenter;

public class RegistrationActivity extends AppCompatActivity implements RegistrationInterface.View, View.OnClickListener {
    private RegistrationPresenter presenter;
    private Button signinButton, registerButton;
    private AlertDialog signInDialog, registerDialog, internetConnectionDialog;
    private LinearLayout rootLinearLayout;
    private android.view.View signInLayout, registerLayout;
    private EditText emailEditTextSi, passwordEditTextSi,
            emailEditTextRe, passwordEditTextRe, firstNameEditText, phoneNumberEditText, lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new RegistrationPresenter(this, this);
        initViews();
        presenter.nowNetworkStateShallBeChecked(this);
    }

    private void initViews() {

        rootLinearLayout = findViewById(R.id.root_linearLayout);
        signinButton = findViewById(R.id.signin_button);
        registerButton = findViewById(R.id.register_button);

        signInLayout = LayoutInflater.from(this).inflate(R.layout.signin_dialog_layout, null);
        emailEditTextSi = signInLayout.findViewById(R.id.email_editText_si);
        passwordEditTextSi = signInLayout.findViewById(R.id.password_editText_si);

        registerLayout = LayoutInflater.from(this).inflate(R.layout.register_dialog_layout, null);
        emailEditTextRe = registerLayout.findViewById(R.id.email_editText_re);
        passwordEditTextRe = registerLayout.findViewById(R.id.password_editText_re);
        firstNameEditText = registerLayout.findViewById(R.id.firstName_editText);
        phoneNumberEditText = registerLayout.findViewById(R.id.phoneNumber_editText);
        lastNameEditText = registerLayout.findViewById(R.id.lastName_editText);

        signInDialog = getSignInDialog();
        registerDialog = getRegisterDialog();

        internetConnectionDialog = getInternetConnectionDialog();

        signinButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private AlertDialog getInternetConnectionDialog() {
        return new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage("internet error")
                .setPositiveButton("Open Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.settings",
                                "com.android.settings.Settings$DataUsageSummaryActivity"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (!presenter.checkNetworkStatus())
                            finish();
                    }
                }).setNegativeButton("Open WIFI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent intent = new Intent(Intent.ACTION_MAIN, null);
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                        intent.setComponent(cn);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create();
    }

    private AlertDialog getSignInDialog() {
        return new AlertDialog.Builder(this)
                .setView(signInLayout)
                .setTitle("Sign in")
                .setMessage("enter correct data")
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (presenter.checkNetworkStatus()) {
                            presenter.nowLocationStateShallBeChecked(RegistrationActivity.this);
                            String accountId = presenter.dialogSignInClicked(RegistrationActivity.this, RegistrationActivity.this, dialog, emailEditTextSi.getText().toString(),
                                    passwordEditTextSi.getText().toString());
                            if (accountId == null) {
                                Toast.makeText(RegistrationActivity.this, "wrong data", Toast.LENGTH_SHORT).show();
                            }else
                            startActivity(new Intent(RegistrationActivity.this,MapsActivity.class));
                        } else
                            Toast.makeText(RegistrationActivity.this, "check your internet", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.dialogCancelClicked(dialog);
                    }
                })
                .create();
    }

    private AlertDialog getRegisterDialog() {
        return new AlertDialog.Builder(this)
                .setView(registerLayout)
                .setTitle("Register")
                .setMessage("enter correct data")
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (presenter.checkNetworkStatus()) {
                            presenter.dialogRegisterClicked(dialog, phoneNumberEditText.getText().toString(), emailEditTextRe.getText().toString(), passwordEditTextRe.getText().toString(),
                                    firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
                        } else
                            Toast.makeText(RegistrationActivity.this, "check your internet", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.dialogCancelClicked(dialog);
                    }
                }).create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_button:
                presenter.signInClicked();
                break;
            case R.id.register_button:
                presenter.registerClicked();
                break;
        }
    }

    @Override
    public void showSignInDialog() {
        signInDialog.show();
    }

    @Override
    public void showRegisterDialog() {
        registerDialog.show();
    }

    @Override
    public void hideDialog(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(rootLinearLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showInternetConnectionDialog() {
        internetConnectionDialog.show();
    }

    @Override
    protected void onResume() {
        presenter.nowNetworkStateShallBeChecked(this);
        super.onResume();
    }
}