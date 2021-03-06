package com.share.teacher.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.download.base.utils.ScreenUtils;
import com.download.update.UpdateMgr;
import com.share.teacher.R;
import com.share.teacher.fragment.TeacherHomePageFragment;
import com.share.teacher.fragment.center.PCenterInfoFragment;
import com.share.teacher.fragment.msg.FeedBackListFragment;
import com.share.teacher.fragment.msg.MsgInfosFragment;
import com.share.teacher.fragment.schedule.ScheduleFragment;
import com.share.teacher.service.LocationService;
import com.share.teacher.service.LocationUitl;
import com.share.teacher.utils.BaseApplication;
import com.share.teacher.utils.SmartToast;

import java.util.ArrayList;

/**
 * 老师端主页面
 */
public class TeacherMainActivity extends BaseActivity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */

    // 未读消息textview
    private TextView unreadLabel;
    // 未读通讯录textview
    private TextView unreadAddressLable;

    private Button[] mTabs;
    private Fragment[] fragments;

    private PCenterInfoFragment pCenterFragment;

    private final int VIEW_COUNT = 4;
    private int index;
    // 当前fragment的index
    private int currentTabIndex;
    Fragment currentFragment = null;
    public LocationUitl locationUitl = new LocationUitl();

    int i=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.getScreenSize(this);
        UpdateMgr.getInstance(this).checkUpdateInfo(null, false);
        setContentView(R.layout.main_activity);
        fragments = new Fragment[VIEW_COUNT];
        fragments[0] =new TeacherHomePageFragment();
        fragments[1] =  new MsgInfosFragment();
//        fragments[2] = new ScheduleFragment();
        fragments[2] = new FeedBackListFragment();
        fragments[3] = pCenterFragment = new PCenterInfoFragment();

        initView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.length;i++){
            transaction.add(R.id.fragment_container,fragments[i]).hide(fragments[i]);

        }
        currentFragment = fragments[0];
        transaction.show(currentFragment);
        transaction.commitAllowingStateLoss();
        reqPerLocation();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        mTabs = new Button[VIEW_COUNT];
        mTabs[0] = (Button) findViewById(R.id.btn_conversation);
        mTabs[1] = (Button) findViewById(R.id.btn_address_list);
        mTabs[2] = (Button) findViewById(R.id.btn_setting);
        mTabs[3] = (Button) findViewById(R.id.btn_center);
        // 把第一个tab设为选中状态
        currentTabIndex = 0;
        mTabs[currentTabIndex].setSelected(true);

        for(int i=0;i<mTabs.length;i++){
            mTabs[i].setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        int index = 0;
        switch (v.getId()){
            case R.id.btn_conversation:
                currentFragment = fragments[0];
                index = 0;
                break;
            case R.id.btn_address_list:
                currentFragment = fragments[1];
                index = 1;
                break;
            case R.id.btn_setting:
                currentFragment = fragments[2];
                index = 2;
                break;
            case R.id.btn_center:
                currentFragment = showSetting();
                index = 3;
                break;
        }

        if(currentTabIndex != index){
            mTabs[currentTabIndex].setSelected(false);
            mTabs[index].setSelected(true);
            hidShow(currentFragment);
            currentTabIndex = index;
        }
    }

    private void hidShow(Fragment newf){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.length;i++){
            transaction.hide(fragments[i]);
        }
        transaction.show(newf);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 显示购物车界面
     * 1.未登录
     * 2.已登录，购物车为空
     * 3.已登录，购物车不为空
     */
    private Fragment showShopCar(){
        return null;
    }

    /**
     * @return
     */
    private Fragment showSetting(){
            return pCenterFragment;
    }
    private long firstTime = 0;
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {// 如果两次按键时间间隔大于2秒，则不退出
                SmartToast.makeText(this,
                        getString(R.string.exit), Toast.LENGTH_SHORT).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            } else {
                System.gc();
                System.exit(0);// 否则退出程序
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationUitl.stopLocation();
    }

    private void reqPerLocation(){
        String[] perStrs = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_PHONE_STATE};

        ArrayList<String> list = new ArrayList<>();
        for(String p:perStrs){
            if(ContextCompat.checkSelfPermission(this,p) != PackageManager.PERMISSION_GRANTED){
                list.add(p);
            }
        }

        if(list.size()==0){
            BaseApplication.getInstance().locationService = new LocationService(getApplicationContext());
            locationUitl.startLocation();
        }else {
            boolean isShowDlg = false;
            for(String p:list){
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,p)){
                    isShowDlg = true;
                    break;
                }
            }

            if(isShowDlg){
                new AlertDialog.Builder(this)
                        .setMessage("需要开启权限才能定位")
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + BaseApplication.getInstance().getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }else {
                ActivityCompat.requestPermissions(this,perStrs,100);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 100){
            boolean isGranted = true;
            for(String p:permissions){
                if(ContextCompat.checkSelfPermission(this,p) != PackageManager.PERMISSION_GRANTED){
                    isGranted = false;
                    break;
                }
            }

            if(isGranted){
                BaseApplication.getInstance().locationService = new LocationService(getApplicationContext());
                locationUitl.startLocation();
            }else {
                SmartToast.showText("您已关闭定位权限!");
            }

        }
    }

}
