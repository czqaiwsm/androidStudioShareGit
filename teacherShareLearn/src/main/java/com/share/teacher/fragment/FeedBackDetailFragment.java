package com.share.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.share.teacher.R;


/**
 *钱包
 * @author czq
 * @time 2015年9月28日上午11:44:26
 *
 */
public class FeedBackDetailFragment extends BaseFragment  {

    private TextView rechargePrice ;
    private String title = "";
    private String content = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

        initTitleView();
        initView(view);
    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText(title);
        setLeftHeadIcon(0);
    }

    private void initView(View v) {
        rechargePrice = (TextView) v.findViewById(R.id.recharge_price);
        if(!TextUtils.isEmpty(content)){
            rechargePrice.setText(content);
        }

    }


}
