package com.example.hangout_v0.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
import com.example.hangout_v0.UserMainActivity;
import com.example.hangout_v0.Vendor.VendorMainActivity;

import java.util.concurrent.TimeUnit;

import static com.google.android.gms.tasks.Tasks.await;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private Button btn_login;
    private Button btn_skip;
    private Button btn_signup;
    private EditText userName;
    private EditText userPassword;
    private CheckBox cb_vendor;
    private boolean is_vendor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        this.getSupportActionBar().hide();
        btn_login = (Button) findViewById(R.id.login_login_button);
        btn_skip = (Button) findViewById(R.id.login_skip_button);
        cb_vendor = (CheckBox) findViewById(R.id.login_vendorTunnel_checkbox);
        btn_signup = findViewById(R.id.login_signup_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(is_vendor == false){
                    HangOutApi.signInCustomer(userName.getText().toString(), userPassword.getText().toString());
                    switchToUserPage();
                }
                else{
                    switchToVendorPage();

                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, com.example.hangout_v0.Login.SignUp.class);
                startActivity(intent);
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserPage();
            }
        });

        cb_vendor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    is_vendor = true;
                }else{
                    is_vendor = false;
                }
            }
        });

    }

    private void init(){
        userName = (EditText) findViewById(R.id.login_userName_editText);
        userPassword = (EditText) findViewById(R.id.login_userPassword_editText);
        ImageView unameClear = (ImageView) findViewById(R.id.login_userName_clear);
        ImageView pwdClear = (ImageView) findViewById(R.id.login_userPassword_clear);

        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(userName,unameClear);
        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(userPassword,pwdClear);
    }

    private void switchToUserPage(){
        Intent myIntent = new Intent(this, UserMainActivity.class);
        startActivity(myIntent);
    }

    private void switchToVendorPage(){
        Intent myIntent = new Intent(this, VendorMainActivity.class);
        startActivity(myIntent);
    }


}