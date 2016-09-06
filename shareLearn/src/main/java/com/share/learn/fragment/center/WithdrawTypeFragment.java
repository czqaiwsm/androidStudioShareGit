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
import com.share.learn.fragment.BaseFragment;
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


    private int withDrawType = 1;//
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

    private void initView(){
        alipayLL.setOnClickListener(this);
        wxPaylipayLL.setOnClickListener(this);
        bankLL.setOnClickListener(this);
        rechargeQuery.setOnClickListener(this);
        withDrawType = getType();
    }

    private int getType(){
        if(alipayBox.isChecked()){
            return 1;
        }
        if(wxPayBox.isChecked()){
            return 2;
        }
        if(bankBox.isChecked()){
            return 3;
        }

        return 0;
    }

    private void checkChange(CheckBox checkBox){
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
                if(bankBox.isChecked()){
                    intent = new Intent(mActivity, ServiceProtocolActivity.class);
                    intent.putExtra("title","提现到银行卡");
                    intent.putExtra("url", URLConstants.BASE_DOMAIN+"/learn-wap/html/service_agreement.html");
                    mActivity.startActivity(intent);
                }else {
                    intent = new Intent(mActivity, WidthDrawActivity.class);
                    intent.putExtra("drawType",withDrawType);
//                intent.putExtra("balance",balance);
//                intent.putExtra("releaName",releaName);
//                intent.putExtra("account",account  );
                startActivityForResult(intent,00);
                }
                break;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
