package com.share.teacher.fragment.center;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.share.teacher.R;
import com.share.teacher.activity.center.DetailActivity;
import com.share.teacher.activity.center.RechargeActivity;
import com.share.teacher.activity.center.ServiceProtocolActivity;
import com.share.teacher.activity.center.WidthDrawActivity;
import com.share.teacher.activity.center.WithdrawTypeActivity;
import com.share.teacher.bean.BalanceInfo;
import com.share.teacher.bean.UserInfo;
import com.share.teacher.fragment.BaseFragment;
import com.share.teacher.help.RequestHelp;
import com.share.teacher.help.RequsetListener;
import com.share.teacher.parse.BaseInfoParse;
import com.share.teacher.parse.BaseParse;
import com.share.teacher.utils.BaseApplication;
import com.share.teacher.utils.SPUtils;
import com.share.teacher.utils.URLConstants;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 *钱包
 * @author czq
 * @time 2015年9月28日上午11:44:26
 *
 */
public class WalletFragment extends BaseFragment implements OnClickListener,RequsetListener {

//    private RelativeLayout photo_layout;
//    private RelativeLayout name_layout;
    private RelativeLayout balance_layout;//余额
    private RelativeLayout recharge_layout;//充值
    private RelativeLayout withDraw_layout;//提现
    private RelativeLayout withDrawDetail_layout;//提现明细


    private TextView account_balance;


   private int recharge = 0x10;
    private int withDraw = 0x11;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            requestTask();
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleView();
        initView(view);
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter("com.share.learn.fragment.center.WalletFragment"));

    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText(R.string.wallet);
        setLeftHeadIcon(0);
        setHeaderRightText(R.string.detail, new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 明细 todo
             toClassActivity(WalletFragment.this, DetailActivity.class.getName());
            }
        });

        requestTask();
    }

    private void initView(View v) {
        balance_layout = (RelativeLayout) v.findViewById(R.id.balance_layout);
        recharge_layout = (RelativeLayout) v.findViewById(R.id.recharge_layout);
        withDraw_layout = (RelativeLayout) v.findViewById(R.id.withDraw_layout);
        withDrawDetail_layout = (RelativeLayout) v.findViewById(R.id.withDrawDetail_layout);
        account_balance = (TextView)v.findViewById(R.id.account_balance);
        balance_layout.setOnClickListener(this);
        recharge_layout.setOnClickListener(this);
        withDraw_layout.setOnClickListener(this);
        withDrawDetail_layout.setOnClickListener(this);
        recharge_layout.setVisibility(View.GONE);

    }


    private Intent intent ;
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.balance_layout:// 余额
            break;
            case R.id.recharge_layout:// 充值
            intent = new Intent(mActivity, RechargeActivity.class);
            startActivityForResult(intent,recharge);
            break;
            case R.id.withDraw_layout:// 提现
                intent = new Intent(mActivity, WithdrawTypeActivity.class);
                startActivityForResult(intent,withDraw);
                break;
            case R.id.withDrawDetail_layout:// 提现明细
                UserInfo userInfo = BaseApplication.getUserInfo();
                BaseApplication application = BaseApplication.getInstance();
                String userId = userInfo != null?userInfo.getId():"";
                intent = new Intent(mActivity, ServiceProtocolActivity.class);
                intent.putExtra("title","提现明细");
                intent.putExtra("url",URLConstants.WITHDRAW_DETAIL+"?userId="+userId+"&appVersion="+application.appVersion+"&clientType=3&accessToken="+BaseApplication.getMt_token()+"&deviceId="+BaseApplication.diviceId);
                mActivity.startActivity(intent);
                break;
        }

    }

    /**
     * 重新登录
     */
    private void reLogin() {
//        startActivityForResult(new Intent(getActivity(), LoginActivity.class), Constant.RELOGIN);
    }

    /**
     * 请求 用户信息
     */
    @Override
    public void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = RequestHelp.getBaseParaMap("QueryBalance");
        RequestParam param = new RequestParam();
//        param.setmParserClassName(BaseParse.class.getName());
        param.setmParserClassName(new BaseInfoParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(), createMyReqErrorListener(), param);
    }

    int balance = 0;
    @Override
    public void handleRspSuccess(int requestType,Object obj)  {
        BalanceInfo balanceInfo = (BalanceInfo) ((JsonParserBase)obj).getData();
        if(balanceInfo == null) return;
        balance  =  Integer.valueOf(balanceInfo.getBalance());
        account_balance.setText(String.format(getResources().getString(R.string.balance_has),balance+"") );
        SPUtils.saveObj2SP(mActivity,balanceInfo,"balanceInfo");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            requestTask();
        }
    }


}
