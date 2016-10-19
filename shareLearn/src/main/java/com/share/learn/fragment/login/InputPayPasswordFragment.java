package com.share.learn.fragment.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.bean.VerifyCode;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.BaseParse;
import com.share.learn.parse.LoginInfoParse;
import com.share.learn.parse.VerifyCodeParse;
import com.share.learn.utils.BaseApplication;
import com.share.learn.utils.SmartToast;
import com.share.learn.utils.URLConstants;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InputPayPasswordFragment extends BaseFragment implements OnClickListener, RequsetListener {

    @Bind(R.id.pass1Et)
    EditText pass1Et;
    @Bind(R.id.pass2Et)
    EditText pass2Et;
    @Bind(R.id.pass3Et)
    EditText pass3Et;
    @Bind(R.id.pass4Et)
    EditText pass4Et;
    @Bind(R.id.pass5Et)
    EditText pass5Et;
    @Bind(R.id.pass6Et)
    EditText pass6Et;
    @Bind(R.id.passLl)
    LinearLayout passLl;
    @Bind(R.id.forget_next)
    TextView forgetNext;
    @Bind(R.id.descTv)
    TextView descTv;

    private ArrayList<EditText> inputList = new ArrayList<>();

    private String pass = "";
    private boolean isSetPass = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_pay_pass, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initTitleView();
        forgetNext.setOnClickListener(this);
        inputList.add(pass1Et);
        inputList.add(pass2Et);
        inputList.add(pass3Et);
        inputList.add(pass4Et);
        inputList.add(pass5Et);
        inputList.add(pass6Et);
        for(EditText e : inputList) {
            e.addTextChangedListener(new TextChangeListener(e));
        }
        setFocus(pass1Et, true);
    }

    private void initTitleView() {
        setTitleText(R.string.set_pay_pass);
        setLeftHeadIcon(0);
    }

    private void setFocus(EditText e, boolean isFocus) {
        e.clearFocus();
        e.setFocusable(true);
        e.setEnabled(true);
        e.requestFocus();
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.forget_next:
                if (!isSetPass) {
                    for(EditText e : inputList) {
                        pass += e.getText().toString().trim();
                    }
                    if (TextUtils.isEmpty(pass)) {
                        SmartToast.showText("请输入完整密码!");
                        return;
                    }
                    descTv.setText("请确认平台支付密码");
                    forgetNext.setText("确定");
                    isSetPass = true;
                    for(EditText e : inputList) {
                        e.setText("");
                        setFocus(e, false);
                    }
                    setFocus(pass1Et, true);
                } else {
                    String tepPhone = "";
                    for(EditText e : inputList) {
                        tepPhone += e.getText().toString().trim();
                    }
                    if (TextUtils.isEmpty(tepPhone)) {
                        SmartToast.showText("请输入完整密码!");
                        return;
                    }
                    if (!pass.equals(tepPhone)) {
                        SmartToast.showText("两次密码输入不一致!");
                        return;
                    }
                    requestTask();
                }

                break;
            default:
                break;
        }
    }


    @Override
    public void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = RequestHelp.getBaseParaMap("SetUpPassword") ;
        postParams.put("payPassword", pass);
        postParams.put("newPayPassword",pass);
        RequestParam param = new RequestParam();
        param.setmParserClassName(new BaseParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(),createMyReqErrorListener(), param);
    }


    @Override
    public void handleRspSuccess(int requestType, Object obj) {
        SmartToast.showText("支付密码设置成功!");
        BaseApplication.getInstance().getUserInfo().setPayFlag(true);
        BaseApplication.saveUserInfo(BaseApplication.getUserInfo());
        mActivity.finish();
    }

    @Override
    protected void failRespone() {
        super.failRespone();
    }

    public class TextChangeListener implements TextWatcher {
        private EditText editText;

        public TextChangeListener(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (editText.getId()) {
                case R.id.pass1Et:
                    if (!TextUtils.isEmpty(s.toString()))
                        setFocus(pass2Et, true);
                    break;
                case R.id.pass2Et:
                    if (!TextUtils.isEmpty(s.toString()))
                        setFocus(pass3Et, true);
                    break;
                case R.id.pass3Et:
                    if (!TextUtils.isEmpty(s.toString()))
                        setFocus(pass4Et, true);
                    break;
                case R.id.pass4Et:
                    if (!TextUtils.isEmpty(s.toString()))
                        setFocus(pass5Et, true);
                    break;
                case R.id.pass5Et:
                    if (!TextUtils.isEmpty(s.toString()))
                        setFocus(pass6Et, true);
                    break;
                case R.id.pass6Et:
                    break;
            }
        }
    }

}
