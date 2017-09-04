package com.share.learn.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.share.learn.fragment.ChooseCitytFragment;
import com.share.learn.fragment.FeedBackDetailFragment;

public class FeedBackDetailActivity extends BaseActivity {
    private FeedBackDetailFragment mFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        onInitContent();
    }

    private void onInitContent() {

        mFragment = (FeedBackDetailFragment) Fragment.instantiate(this,
                FeedBackDetailFragment.class.getName());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(android.R.id.content, mFragment);
        ft.commit();
    }
}
