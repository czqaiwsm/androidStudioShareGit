package com.share.learn.activity.teacher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.share.learn.activity.BaseActivity;
import com.share.learn.fragment.home.SchoolCertifyFragment;

public class SchoolCertifyActivity extends BaseActivity {
	private SchoolCertifyFragment mFragment;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		onInitContent();
	}

	private void onInitContent() {
		mFragment = (SchoolCertifyFragment) Fragment.instantiate(this, SchoolCertifyFragment.class.getName());
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(android.R.id.content, mFragment);
		ft.commit();
	}
}
