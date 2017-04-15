package com.example.thinkpad.telematics;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.thinkpad.telematics.activities.LoginActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;



public class MainActivity extends AppCompatActivity implements OnMenuTabClickListener
        , Drawer.OnDrawerItemClickListener
        , AccountHeader.OnAccountHeaderListener {
    //DrawerItem
    public static final int DRAWER_ITEM_MATENCE_CAR = 2;
    public static final int DRAWER_ITEM_MY_CAR = 3;
    public static final int DRAWER_ITEM_MY_ORDER = 4;
    public static final int DRAWER_ITEM_MY_INFO = 5;
    public static final int DRAWER_ITEM_MY_REGULATION = 6;
    public static final int DRAWER_ITEM_ABOUT = 7;
    public static final int DRAWER_ITEM_FEEDBACK = 8;
    public static final int DRAWER_ITEM_SETTING = 9;
    public static final int DRAWER_ITME_UPDATE = 10;

    public static final int DRAWER_ITEM_NOW_PLAYING = 11;

    public static final int PROFILE_ITEM_NO_USER = 21;
    public static final int PROFILE_ITEM_REGISTER = 22;
    public static final int PROFILE_ITEM_USER = 23;

    public static final int PROFILE_ITEM_DEFAULT_POSITION = 0;

    //MainActivity的实例
    public static MainActivity mInstance = null;

    //BottomBar
    private BottomBar mBottomBar;

    //MaterialDrawer
    private AccountHeader mAccountHeader;
    private Drawer mDrawer;

    //Toolbar
    private Toolbar mToolbar;
    //Handler
    private Handler mHandler = new Handler();

    Runnable nagToLogIn = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;

        initToolbar();

        initBottomBar(savedInstanceState);

        initMaterialDrawer(savedInstanceState);
    }
    //初始化Toolbar
    private void initToolbar() {

        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
    }

    //初始化BottomBar
    private void initBottomBar(Bundle savedInstanceState) {

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.useFixedMode();
        mBottomBar.setItems(R.menu.menu_bottombar);
        mBottomBar.setOnMenuTabClickListener(this);

        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorPrimary));

    }

    @Override
    public boolean onProfileChanged(View view, IProfile profile, boolean current) {

        Runnable runnable = null;

        //处理点击事件
       if (profile.getIdentifier() == PROFILE_ITEM_NO_USER) {

            runnable = nagToLogIn;
        }

        if (runnable != null) {

            mHandler.postDelayed(runnable, 300);
        }
        return false;
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        Runnable runnable = null;

        return false;
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
    //初始化MaterialDrawer
    private void initMaterialDrawer(Bundle savedInstanceState) {

        //DrawerItem
        PrimaryDrawerItem itemMyOrder = new PrimaryDrawerItem();
        itemMyOrder.withIdentifier(DRAWER_ITEM_MY_ORDER)
                .withName(R.string.drawer_item_my_order)
                .withIcon(GoogleMaterial.Icon.gmd_assignment);

        PrimaryDrawerItem itemMyCar = new PrimaryDrawerItem();
        itemMyCar.withIdentifier(DRAWER_ITEM_MY_CAR)
                .withName(R.string.drawer_item_my_car)
                .withIcon(GoogleMaterial.Icon.gmd_car);

        PrimaryDrawerItem itemMyInfo = new PrimaryDrawerItem();
        itemMyInfo.withIdentifier(DRAWER_ITEM_MY_INFO)
                .withName(R.string.drawer_item_my_info)
                .withIcon(GoogleMaterial.Icon.gmd_account);

        PrimaryDrawerItem itemMyRegulation = new PrimaryDrawerItem();
        itemMyRegulation.withIdentifier(DRAWER_ITEM_MY_REGULATION)
                .withName(R.string.drawer_item_my_regulation)
                .withIcon(FontAwesome.Icon.faw_exclamation_triangle);

        PrimaryDrawerItem itemMatenceCar = new PrimaryDrawerItem();
        itemMatenceCar.withIdentifier(DRAWER_ITEM_MATENCE_CAR)
                .withName(R.string.drawer_item_matence_car)
                .withIcon(FontAwesome.Icon.faw_wrench);

        PrimaryDrawerItem itemNowPlaying = new PrimaryDrawerItem();
        itemNowPlaying.withIdentifier(DRAWER_ITEM_NOW_PLAYING)
                .withName(R.string.drawer_item_now_playing)
                .withIcon(GoogleMaterial.Icon.gmd_equalizer);

        SecondaryDrawerItem itemSetting = new SecondaryDrawerItem();
        itemSetting.withIdentifier(DRAWER_ITEM_SETTING)
                .withName(R.string.drawer_item_setting)
                .withIcon(GoogleMaterial.Icon.gmd_settings);

        SecondaryDrawerItem itemAbout = new SecondaryDrawerItem();
        itemAbout.withIdentifier(DRAWER_ITEM_ABOUT)
                .withName(R.string.drawer_item_about)
                .withIcon(GoogleMaterial.Icon.gmd_info);

        SecondaryDrawerItem itemFeedBack = new SecondaryDrawerItem();
        itemFeedBack.withIdentifier(DRAWER_ITEM_FEEDBACK)
                .withName(R.string.drawer_item_feedback)
                .withIcon(GoogleMaterial.Icon.gmd_adb);

        SecondaryDrawerItem itemUpdate = new SecondaryDrawerItem();
        itemUpdate.withIdentifier(DRAWER_ITME_UPDATE)
                .withName(R.string.drawer_item_update)
                .withIcon(GoogleMaterial.Icon.gmd_refresh);

        ProfileSettingDrawerItem proRegister = new ProfileSettingDrawerItem();
        proRegister.withIdentifier(PROFILE_ITEM_REGISTER)
                .withName("立即注册")
                .withIcon(GoogleMaterial.Icon.gmd_account_add);

        ProfileDrawerItem proAccount = new ProfileDrawerItem();
        proAccount.withIdentifier(PROFILE_ITEM_NO_USER)
                .withName("未登录")
                .withEmail("点击登录或者注册")
                .withIcon(R.drawable.profile)
                .withNameShown(true);


        // Create the AccountHeader
        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        proAccount,
                        proRegister
                )
                .withOnAccountHeaderListener(this)
                .withSavedInstance(savedInstanceState)
                .build();



        //Create the drawer
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(mAccountHeader) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        itemMyInfo,
                        itemMyCar,
                        itemMyOrder,
                        itemMyRegulation,
                        itemMatenceCar,
                        new DividerDrawerItem(),
                        itemNowPlaying,
                        new DividerDrawerItem(),
                        itemAbout,
                        itemSetting,
                        itemFeedBack,
                        itemUpdate
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(this)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

    }
}
