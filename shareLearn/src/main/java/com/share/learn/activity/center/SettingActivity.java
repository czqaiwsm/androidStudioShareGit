package com.share.learn.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.share.learn.activity.BaseActivity;
import com.share.learn.activity.login.LoginActivity;
import com.share.learn.fragment.center.SettingFragmentUser;

public class SettingActivity extends BaseActivity {
    private SettingFragmentUser mFragment ;

    int count = 0;
    public static boolean exit = false;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        onInitContent();
        count ++;
        exit = false;
    }

    private void onInitContent() {
        mFragment = (SettingFragmentUser) Fragment.instantiate(this,
                SettingFragmentUser.class.getName());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(android.R.id.content, mFragment);
        ft.commit();
    }

    @Override
    public void finish() {
        super.finish();
        if(count == 1 && exit)
        startActivity(new Intent(this, LoginActivity.class));
    }
}
