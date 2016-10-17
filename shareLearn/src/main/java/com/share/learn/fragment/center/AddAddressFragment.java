package com.share.learn.fragment.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.bean.AddressInfos;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.BaseInfoParse;
import com.share.learn.utils.SmartToast;
import com.share.learn.utils.URLConstants;
import com.share.learn.view.AddPopwindow;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;

import java.util.Map;

/**
 *钱包
 * @author czq
 * @time 2015年9月28日上午11:44:26
 *
 */
public class AddAddressFragment extends BaseFragment implements OnClickListener,RequsetListener{

    private RelativeLayout balance_layout;//余额
    private RelativeLayout withDraw_layout;//提现

    private EditText account_balance;
    private TextView account_withDraw;
    private AddPopwindow popwindow = null;
    private int editType = 1;        //1.新增 2.修改
    private AddressInfos.AddressInfo addressInfo = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            editType = intent.hasExtra("editType")?intent.getIntExtra("editType",1):1;
            addressInfo = (AddressInfos.AddressInfo)(intent.hasExtra("address")?intent.getSerializableExtra("address"):null);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleView();
        initView(view);
    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText("新增地址");
        setLeftHeadIcon(0);
        setHeaderRightText("保存", new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(account_balance.getText()) || TextUtils.isEmpty(account_withDraw.getText())){
                    SmartToast.showText("请完善地址信息!");
                    return;
                }
                requestTask();
            }
        });

    }

    private void initView(View v) {
        balance_layout = (RelativeLayout) v.findViewById(R.id.balance_layout);
        withDraw_layout = (RelativeLayout) v.findViewById(R.id.withDraw_layout);
        account_balance = (EditText)v.findViewById(R.id.account_balance);
        account_withDraw = (TextView)v.findViewById(R.id.account_withDraw);
        popwindow = new AddPopwindow(mActivity,this);

//        photo_layout.setOnClickListener(this);
//        name_layout.setOnClickListener(this);
        balance_layout.setOnClickListener(this);
        withDraw_layout.setOnClickListener(this);

        if(addressInfo != null){
            account_withDraw.setText(addressInfo.getAreaAddress());
            account_balance.setText(addressInfo.getDetailAddress());
        }

    }


    private Intent intent ;
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.withDraw_layout:// 地址
                popwindow.payPopShow(v);
            break;
            case R.id.sureBtn:
                account_withDraw.setText(v.getTag().toString());
                break;
        }

    }


    /**
     * 请求 用户信息
     */
    @Override
    public void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = RequestHelp.getBaseParaMap("EditAddress");
        postParams.put("editType",editType);   //操作类型，1.新增 2.修改
        postParams.put("addressId",addressInfo == null?"":addressInfo.getAddressId());
        postParams.put("areaAddress",account_withDraw.getText().toString());
        postParams.put("detailAddress",account_balance.getText().toString());
        RequestParam param = new RequestParam();
        param.setmParserClassName(new BaseInfoParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(), createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType,Object obj)  {
        mActivity.setResult(100);
        mActivity.finish();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
