package com.share.learn.fragment.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.activity.center.ServiceProtocolActivity;
import com.share.learn.activity.center.WidthDrawActivity;
import com.share.learn.bean.UserInfo;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.utils.AlertDialogUtils;
import com.share.learn.utils.BaseApplication;
import com.share.learn.utils.URLConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 钱包
 *
 * @author czq
 * @time 2015年9月28日上午11:44:26
 */
public class WithdrawTypeFragment extends BaseFragment implements OnClickListener {


    @Bind(R.id.alipayBox)
    CheckBox alipayBox;
    @Bind(R.id.alipayLL)
    LinearLayout alipayLL;
    @Bind(R.id.wxPayBox)
    CheckBox wxPayBox;
    @Bind(R.id.wxPaylipayLL)
    LinearLayout wxPaylipayLL;
    @Bind(R.id.bankBox)
    CheckBox bankBox;
    @Bind(R.id.bankLL)
    LinearLayout bankLL;
    @Bind(R.id.recharge_query)
    TextView rechargeQuery;


    private int withDrawType = 1;//1-支付宝，2-微信，3-银行卡

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_withdraw_type, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleView();
        initView();
    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText("提现到");
        setLeftHeadIcon(0);

    }

    private void initView() {
        alipayLL.setOnClickListener(this);
        wxPaylipayLL.setOnClickListener(this);
        bankLL.setOnClickListener(this);
        rechargeQuery.setOnClickListener(this);
        withDrawType = getType();

        if(!BaseApplication.getUserInfo().getPayFlag()){
            AlertDialogUtils.displayMyAlertChoice(mActivity, "提示", "您还没设置支付密码,请去设置!", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 16/10/19
                }
            }, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }

    }

    private int getType() {
        if (alipayBox.isChecked()) {
            return 1;
        }
        if (wxPayBox.isChecked()) {
            return 2;
        }
        if (bankBox.isChecked()) {
            return 3;
        }

        return 0;
    }

    private void checkChange(CheckBox checkBox) {
        alipayBox.setChecked(false);
        wxPayBox.setChecked(false);
        bankBox.setChecked(false);

        checkBox.setChecked(true);
        withDrawType = getType();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.alipayLL:
                checkChange(alipayBox);
                break;
            case R.id.wxPaylipayLL:
                checkChange(wxPayBox);
                break;
            case R.id.bankLL:
                checkChange(bankBox);
                break;
            case R.id.recharge_query://下一步
                Intent intent = null;
                if (bankBox.isChecked()) {
                    UserInfo userInfo = BaseApplication.getUserInfo();
                    BaseApplication application = BaseApplication.getInstance();
                    String userId = userInfo != null ? userInfo.getId() : "";
                    intent = new Intent(mActivity, ServiceProtocolActivity.class);
                    intent.putExtra("title", "提现到银行卡");
                    intent.putExtra("url", URLConstants.BANK_WITHDRAW + "?userId=" + userId + "&appVersion=" + application.appVersion + "&clientType=3&accessToken=" + BaseApplication.getMt_token() + "&deviceId=" + BaseApplication.diviceId);
                    mActivity.startActivity(intent);
                } else {
                    intent = new Intent(mActivity, WidthDrawActivity.class);
                    intent.putExtra("drawType", withDrawType);
//                intent.putExtra("balance",balance);
//                intent.putExtra("releaName",releaName);
//                intent.putExtra("account",account  );
                    startActivityForResult(intent, 00);
                }
                getActivity().finish();
                break;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
