package com.example.thinkpad.telematics.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinkpad.telematics.R;
import com.example.thinkpad.telematics.utils.PreferencesUtils;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    //View
    private AutoCompleteTextView mUserName;
    private EditText mUserPassword;
    private CheckBox mSavePassword;
    private CheckBox mAutoLogin;
    private Button mLogin;

    private ProgressBar mProgressBar;
    private ScrollView mScrollView;

    private TextView mRegister;
    private TextView mForgot;

    //上下文
    private Context mContext;

    private String userName;
    private String userPassword;


    //
    private static final int REQUEST_READ_CONTACTS = 0;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        this.mContext = getContext();

    }

    //是否具有权限  没有的话  在运行时 申请一下 权限
    private boolean isPermission() {

        //当  android 版本低于M的时候  只需要 在 AndroidManifest 添加权限就行
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;
        }

        //判断 是否具有 运行时权限
        if (mContext.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            return true;
        }

        //是否能申请
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {

//            //给用户提示
//            new SnackBar(getActivity(), "亲，可以读取你的联系人么？", "没问题", new View.OnClickListener() {
//
//                @Override
//                @TargetApi(Build.VERSION_CODES.M)       //版本问题
//                public void onClick(View v) {
//
//                    requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                }
//            });

        } else {

            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }

        return false;
    }

    //申请权限回掉
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //权限申请成功
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        setRootView(rootView);

        return rootView;
    }

    //可获得用户信息接口
    private void setRootView(View rootView) {

        findView(rootView);

        setListener();

        setSomeThing();
    }

    //设置一些东西
    private void setSomeThing() {
// TODO: 2017/4/8  
    }

    //toSetSomeThing
    private void toSetSomeThing() throws Exception {

        // TODO: 2017/4/8  
    }

    //设置  监听器
    private void setListener() {

        mAutoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mSavePassword.isChecked()) {

                    mSavePassword.setChecked(true);
                }
            }
        });

        mSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mSavePassword.isChecked()) {

                    if (mAutoLogin.isChecked()) mAutoLogin.setChecked(false);
                }
            }
        });


    }

    //找到 相应的View
    private void findView(View rootView) {

        //用户名
        mUserName = (AutoCompleteTextView) rootView.findViewById(R.id.fragment_login_autocompletetextview_user_name);

        //密码
        mUserPassword = (EditText) rootView.findViewById(R.id.fragment_login_edittext_user_password);

        //保存密码
        mSavePassword = (CheckBox) rootView.findViewById(R.id.fragment_login_checkbox_save_password);

        //自动登录
        mAutoLogin = (CheckBox) rootView.findViewById(R.id.fragment_login_checkbox_auto_login);

        //登陆按钮
        mLogin = (Button) rootView.findViewById(R.id.fragment_login_button_login_now);

        //跳转到注册页面
        mRegister = (TextView) rootView.findViewById(R.id.fragment_login_textview_register);

        //bar
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.fragment_login_progressbar_loading);

        //content
        mScrollView = (ScrollView) rootView.findViewById(R.id.fragment_login_scrollview_content);

        mForgot = (TextView) rootView.findViewById(R.id.fragment_login_textview_forgot);
    }

    //登陆前的校验
    private boolean checkBeforeLogin(String userName, String userPassword) {

        // TODO: 2017/4/8

        return true;
    }

    //返回到登陆之前的状态
    private void reBackToLogin() {

        mProgressBar.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
    }


}
