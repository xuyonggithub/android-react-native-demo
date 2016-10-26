package com.shengjing.industry.industrydemo.activity;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.shengjing.industry.industrydemo.BuildConfig;
import com.shengjing.industry.industrydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  implements DefaultHardwareBackBtnHandler{

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    Fragment mMainFragment;
    Fragment mListFragment;
    Fragment mMeFragment;

    android.support.v7.app.ActionBar actionBar;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        showFragment(mMainFragment);
/*        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);*/
/*    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());*/

        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(this.getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                //.setUseOldBridge(true) // uncomment this line if your app crashes
                .build();

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list:
                        showFragment(mListFragment);
                        break;
                    case R.id.main:
                        showFragment(mMainFragment);
                        break;
                    case R.id.me:
                        showFragment(mMeFragment);
                        break;
                }
                return false;
            }
        });
    }

    private void initFragment() {
        mMainFragment = getSupportFragmentManager().findFragmentById(R.id.frag_main);
        mListFragment = getSupportFragmentManager().findFragmentById(R.id.frag_list);
        mMeFragment = getSupportFragmentManager().findFragmentById(R.id.frag_me);
    }

    private void showFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().hide(mListFragment).hide(mMeFragment).hide(mMainFragment).show(frag).commit();
    }

/*    *//**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     *//*
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }*/


    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

/*    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public ReactInstanceManager reactInstanceManager() {
        return mReactInstanceManager;
    }
 }
