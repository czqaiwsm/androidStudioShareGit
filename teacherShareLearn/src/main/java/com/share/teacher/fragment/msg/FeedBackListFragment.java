package com.share.teacher.fragment.msg;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.share.teacher.R;
import com.share.teacher.adapter.OrderPageFragmentAdapter;
import com.share.teacher.fragment.BaseFragment;
import com.share.teacher.fragment.center.OrderPayFragment;
import com.share.teacher.fragment.feedback.FeedBackFragment;
import com.share.teacher.fragment.login.LoginFramgent;

import java.util.ArrayList;
import java.util.List;

public class FeedBackListFragment extends BaseFragment implements View.OnClickListener{

    private ViewPager mViewPager;
    private final int  viewCount = 2;
    private ImageView[] imageViews = new ImageView[viewCount];
    private RelativeLayout[] rLayouts = new RelativeLayout[viewCount];
    private TextView[] textViews = new TextView[viewCount];

    public  static final int PAY_SUCC = 0X12;
    public  static final int CONFIRM_ORDER = 0X22;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PAY_SUCC:
                    mViewPager.setCurrentItem(1,true);
                    break;
                case CONFIRM_ORDER:
                    mViewPager.setCurrentItem(2,true);
                    break;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // startReqTask(this);
        // mLoadHandler.sendEmptyMessageDelayed(Constant.NET_SUCCESS, 100);// 停止加载框
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_feed_back_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTitle();
    }

    private void initTitle(){
        setTitleText("课时反馈");
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.order_viewpager);
        rLayouts[0] = (RelativeLayout)view.findViewById(R.id.hasPayRl);
//        rLayouts[1] = (RelativeLayout)view.findViewById(R.id.hasCancelRl);
        rLayouts[1] = (RelativeLayout)view.findViewById(R.id.donePayRl);

        imageViews[0] = (ImageView)view.findViewById(R.id.hasPayImg);
//        imageViews[1] = (ImageView)view.findViewById(R.id.hasCancelImg);
        imageViews[1] = (ImageView)view.findViewById(R.id.donePayImg);
        textViews[0] = (TextView)view.findViewById(R.id.hasPayTxt);
//        textViews[1] = (TextView)view.findViewById(R.id.hasCancelTxt);
        textViews[1] = (TextView)view.findViewById(R.id.donePayTxt);
        mViewPager.setOffscreenPageLimit(0);
        onInitTabConfig();
    }

    private void onInitTabConfig() {
        List<Fragment> orderPayFragments = new ArrayList<Fragment>();
        for (int i = 0; i < viewCount; i++) {
            rLayouts[i].setOnClickListener(this);

            FeedBackFragment feedBackFragment = new FeedBackFragment();
            feedBackFragment.type = (i + 1);
            feedBackFragment.handler = handler;
            orderPayFragments.add(feedBackFragment);

        }
        mViewPager.setAdapter(new OrderPageFragmentAdapter(getChildFragmentManager(),
                orderPayFragments));
        imageViews[0].setSelected(true);
        textViews[0].setSelected(true);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < viewCount; i++) {
                    imageViews[i].setSelected(false);
                    textViews[i].setSelected(false);
                }
                imageViews[position].setSelected(true);
                textViews[position].setSelected(true);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int index = -1;
        switch (v.getId()){
            case R.id.hasPayRl:
                index = 0;
                break;
//            case R.id.hasCancelRl:
//                index = 1;
//                break;
            case R.id.donePayRl:
                index = 1;
                break;
            default:break;
        }
        changeStatus(index);
    }

    private void changeStatus(int position){
            if(position == mViewPager.getCurrentItem()){
                return;
            }
        mViewPager.setCurrentItem(position,true);
    }

}
