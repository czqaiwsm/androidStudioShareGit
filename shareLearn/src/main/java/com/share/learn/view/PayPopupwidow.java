package com.share.learn.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.alipay.sdk.pay.demo.PayCallBack;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.share.learn.R;
import com.share.learn.activity.teacher.SetPayPasswordActivity;
import com.share.learn.bean.PageInfo;
import com.share.learn.bean.PayInfo;
import com.share.learn.help.RequestHelp;
import com.share.learn.parse.BaseParse;
import com.share.learn.utils.AlertDialogUtils;
import com.share.learn.utils.BaseApplication;
import com.share.learn.utils.PayUtil;
import com.share.learn.utils.SmartToast;
import com.share.learn.utils.URLConstants;
import com.share.learn.utils.WaitLayer;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;
import com.volley.req.parser.ParserUtil;

import java.util.Map;

/**
 * @desc 请用一句话描述此文件
 * @creator caozhiqing
 * @data 2016/1/13
 */
public class PayPopupwidow implements View.OnClickListener{

    private Activity activity;
    private  PopupWindow mSortPop;
    private Handler handler;
    private View view;
    private PayInfo payInfo;
    private View.OnClickListener onClickListener;
    private PayCallBack payCallBack;
    private String pass = "";


    public PayPopupwidow(Activity activit, View.OnClickListener onClickListener,PayCallBack payCallBack){
        this.payCallBack = payCallBack;
        this.onClickListener = onClickListener;
        this.activity = activit;
        setmSortPop();
//        mSortPop.showAtLocation(getView(), Gravity.BOTTOM,0,0);
    }


    public void payPopShow(View view,PayInfo news){
        if(view == null){
            view = this.view;
        }
        this.payInfo = news;
        if(mSortPop != null){
            if(BaseApplication.getUserInfo() != null){
//                if(payInfo != null && payInfo.getPrice().compareTo(BaseApplication.getUserInfo().getBalance())>1){
//                    this.view.findViewById(R.id.wxPay).setVisibility(View.GONE);
//                }
            }
            mSortPop.showAtLocation(view, Gravity.BOTTOM,0,0);
        }else {
            setmSortPop();
            payPopShow(view,news);
        }
    }



    private void setmSortPop(){
        LayoutInflater inflater = LayoutInflater.from(activity);
        // 引入窗口配置文件
         view = inflater
                .inflate(R.layout.pay_selector, null);
        // 创建PopupWindow对象
        mSortPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        mSortPop.setAnimationStyle(R.style.popwin_anim_bottom_style);
        // 需要设置一下此参数，点击外边可消失
        mSortPop.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        mSortPop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mSortPop.setFocusable(true);

        view.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortPop.dismiss();
            }
        });

        if(onClickListener == null){
            onClickListener = this;
        }
        view.findViewById(R.id.alipay).setOnClickListener(onClickListener);
        view.findViewById(R.id.wxPay).setOnClickListener(onClickListener);
        view.findViewById(R.id.wallet).setOnClickListener(onClickListener);

//        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dimiss();
//            }
//        });
//        if(BaseApplication.getUserInfo() != null){
//            if(payInfo != null && payInfo.getPrice().compareTo(BaseApplication.getUserInfo().getBalance())>1){
//                view.findViewById(R.id.wxPay).setVisibility(View.GONE);
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alipay://支付宝支付
                PayUtil.alipay(activity,payInfo,payCallBack);
                break;
            case R.id.wallet://余额支付
                if(!BaseApplication.getUserInfo().getPayFlag()){
                    AlertDialogUtils.displayMyAlertChoice(activity, "提示", "您还没设置支付密码,请去设置!", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(activity, SetPayPasswordActivity.class);
                            activity.startActivity(intent);
                        }
                    }, null);
                }else {
                    AlertDialogUtils.displayEditAlert(activity, "支付密码", "", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!TextUtils.isEmpty((pass = view.getTag().toString())) ){
                                validPayPassword(activity,pass);
                            }
                        }
                    }, null);
                }

                break;
            case R.id.wxPay://微信支付
                PayUtil.wxPay(payInfo,payCallBack);
                break;
        }

        if(mSortPop != null && mSortPop.isShowing()){
            mSortPop.dismiss();
        }
    }

    public void dimiss(){

        if(mSortPop != null && mSortPop.isShowing()){
            mSortPop.dismiss();
        }

    }
    public  void validPayPassword(Activity mActivity, String pass){
        final WaitLayer waitLayer = new WaitLayer(mActivity, WaitLayer.DialogType.MODALESS);
        waitLayer.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                waitLayer.dismiss();
            }
        },20000);
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        RequestParam param = new RequestParam();
        Map postParams = RequestHelp.getBaseParaMap("ValidPayPassword");
        postParams.put("payPassword", pass);
        param.setmParserClassName(new BaseParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(mActivity, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object o) {
                waitLayer.dismiss();
                JsonParserBase base = (JsonParserBase) o;
                if(base.getRespCode().equalsIgnoreCase(URLConstants.SUCCESS_CODE)){
                    PayUtil.walletPay(activity,payInfo,payCallBack);
                }else {
                    SmartToast.showText(base.getRespDesc());

                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                waitLayer.dismiss();
            }
        }, param);
    }

    public void setUnVisibleWallet(){
        view.findViewById(R.id.wallet).setVisibility(View.GONE);
        view.findViewById(R.id.secondLine).setVisibility(View.GONE);
    }

}
