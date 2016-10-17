package com.share.learn.activity.teacher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.share.learn.activity.BaseActivity;
import com.share.learn.fragment.home.ChooseAddressFragment;
import com.share.learn.fragment.home.ManageAddressFragment;

public class ManageAddressActivity extends BaseActivity {
	private Fragment mFragment;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		onInitContent();
	}

	private void onInitContent() {
		mFragment =  Fragment.instantiate(this, ManageAddressFragment.class.getName());
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(android.R.id.content, mFragment);
		ft.commit();
	}

}
