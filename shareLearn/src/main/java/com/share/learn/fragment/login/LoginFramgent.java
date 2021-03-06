package com.share.learn.fragment.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.activity.login.ForgetPassActivity;
import com.share.learn.activity.login.RegisterActivity;
import com.share.learn.bean.LoginInfo;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.LoginInfoParse;
import com.share.learn.utils.AppLog;
import com.share.learn.utils.BaseApplication;
import com.share.learn.utils.PhoneUitl;
import com.share.learn.utils.URLConstants;
import com.share.learn.utils.WaitLayer;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * @desc 请用一句话描述此文件
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class LoginFramgent extends BaseFragment implements View.OnClickListener,RequsetListener{

    private EditText login_username;
    private EditText login_pass;
    private TextView  forget_pass_text;
    private TextView login_text;
    private TextView register_text;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pcenter_login,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLoadingDilog(WaitLayer.DialogType.MODALESS);
        setTitleText(R.string.login_title);
        initView(view);
        setLeftHeadIcon(0);
    }
    private void initView(View view){
        login_username = (EditText)view.findViewById(R.id.login_username);
        login_pass = (EditText)view.findViewById(R.id.login_pass);
        forget_pass_text = (TextView)view.findViewById(R.id.forget_pass_text);
        login_text = (TextView)view.findViewById(R.id.login_text);
        register_text = (TextView)view.findViewById(R.id.register_text);

        login_text.setOnClickListener(this);
        register_text.setOnClickListener(this);
        forget_pass_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.login_text:
              if(TextUtils.isEmpty(login_username.getText()) || TextUtils.isEmpty(login_pass.getText())){
                  toasetUtil.showInfo( R.string.passname_empty);
              }else if (!PhoneUitl.isPhone(login_username.getText().toString())){
                  toasetUtil.showInfo(R.string.phone_error);
              }else {
                requestTask();
              }
              break;
          case R.id.register_text:
              toClassActivity(this, RegisterActivity.class.getName());
              break;
          case R.id.forget_pass_text:
              toClassActivity(this, ForgetPassActivity.class.getName());
              break;
          default:break;
      }

    }

    @Override
    protected void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = RequestHelp.getBaseParaMap("UserLogin") ;
        postParams.put("loginName", login_username.getText().toString());
        postParams.put("password",login_pass.getText().toString());
        RequestParam param = new RequestParam();
//        param.setmParserClassName(LoginInfoParse.class.getName());
        param.setmParserClassName(new LoginInfoParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(),createMyReqErrorListener(), param);

    }

    @Override
    public void handleRspSuccess(int requestType,Object obj) {
        JsonParserBase<LoginInfo> jsonParserBase = (JsonParserBase<LoginInfo>)obj;
        if ((jsonParserBase != null)){
            BaseApplication.saveUserInfo(jsonParserBase.getData().getUserInfo());
            BaseApplication.setMt_token(jsonParserBase.getData().getToken()); ;
            BaseApplication.setMt_id(BaseApplication.getUserInfo().getId()
            );
//            toClassActivity(LoginFramgent.this, MainActivity.class.getName());//学生
//            toClassActivity(LoginFramgent.this, TeacherMainActivity.class.getName());//老师
            JPushInterface.setAlias(BaseApplication.getInstance(),"s_"+BaseApplication.getMt_id(),null);
            Set<String> set = new HashSet<String>();
            set.add("suser-学生");
            JPushInterface.setTags(BaseApplication.getInstance(),set,new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                    AppLog.Logi("jpush alias:"+s+"   "+(set!=null?set.toString():""));
                }
            });
            mActivity.setResult(Activity.RESULT_OK);
            mActivity.finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}