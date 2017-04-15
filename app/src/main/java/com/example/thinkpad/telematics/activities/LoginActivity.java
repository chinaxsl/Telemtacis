package com.example.thinkpad.telematics.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.thinkpad.telematics.R;
import com.example.thinkpad.telematics.fragments.*;
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println("1");
        initToolbar();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_login_framelayout,LoginFragment.newInstance()).commit();
    }

    //initToolbar
    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        try {
            setSupportActionBar(mToolbar);
        } catch (Exception e){
            e.printStackTrace();
        }
        ActionBar mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
