package com.share.learn.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.share.learn.R;
import com.share.learn.activity.teacher.InputPayPasswordActivity;
import com.share.learn.bean.VerifyCode;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.VerifyCodeParse;
import com.share.learn.utils.BaseApplication;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;

public class SetPayPasswordFragment extends BaseFragment implements OnClickListener,RequsetListener {

	private TextView phoneNumTv;
	private EditText forget_inputCode;
	private TextView forget_getCode;
	private TextView forget_next;
	private boolean isgetCode = true;

	private int MSG_TOTAL_TIME;
	private final int MSG_UPDATE_TIME = 500;
	private  int requetType = -1;
	private String phone = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// startReqTask(this);
		// mLoadHandler.sendEmptyMessageDelayed(Constant.NET_SUCCESS, 100);// 停止加载框
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_set_pay_password, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initTitleView();
		initView(view);

	}

	private void initTitleView() {
		setTitleText(R.string.set_pay_pass);
		setLeftHeadIcon(0);
	}

	private void initView(View view) {
		phoneNumTv = (TextView) view.findViewById(R.id.phoneNumTv);
		forget_inputCode = (EditText) view.findViewById(R.id.forget_passCode);
		forget_getCode = (TextView) view.findViewById(R.id.forget_getCode);
		forget_next = (TextView) view.findViewById(R.id.forget_next);

		forget_next.setOnClickListener(this);
		forget_getCode.setOnClickListener(this);

		phone = BaseApplication.getInstance().getUserInfo().getMobile();
		phoneNumTv.setText(phone.substring(0,2)+"****"+phone.substring(phone.length()-4,phone.length()));
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.forget_next:
			isgetCode = false;
			onJudge();
			break;
		case R.id.forget_getCode:
			isgetCode = true;
			getCode();
			break;
		default:
			break;
		}
	}

	private void onJudge() {
		if(verifyCode == null || !verifyCode.getSmsCode().equalsIgnoreCase(forget_inputCode.getText().toString())){
			toasetUtil.showInfo("请输入正确的验证码!");
			return;
		}
		Intent intent = new Intent(mActivity, InputPayPasswordActivity.class);
		startActivity(intent);
		mActivity.finish();
	}

	private void getCode() {
				forget_getCode.setEnabled(false);
				MSG_TOTAL_TIME = 60;
				Message message = new Message();
				message.what = MSG_UPDATE_TIME;
				timeHandler.sendMessage(message);
				requetType = 1;
				requestData(0);// ----------发送请求
				forget_getCode.requestFocus();
	}

	// 验证码倒计时
	public Handler timeHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if(isDetached()) {
				removeCallbacksAndMessages(null);
				return;
			}
			switch (msg.what) {
			case MSG_UPDATE_TIME:
				MSG_TOTAL_TIME--;
				if (MSG_TOTAL_TIME > 0) {
					forget_getCode.setText(String.format(getResources().getString(R.string.has_minuter,MSG_TOTAL_TIME+"")));
					forget_getCode.setText(MSG_TOTAL_TIME + " 秒");
					Message message = obtainMessage();
					message.what = MSG_UPDATE_TIME;
					sendMessageDelayed(message, 1000);
				} else if (MSG_TOTAL_TIME < -10) {
					forget_getCode.setText(R.string.addcode_resend);
					forget_getCode.setEnabled(true);
				} else {
					forget_getCode.setText(R.string.addcode_code);
					forget_getCode.setEnabled(true);
				}
				break;
			default:
				break;
			}
		}
	};



	@Override
	public void requestData(int requestType) {
		RequestParam param = null;
		switch (requetType){
			case 1:
				param = RequestHelp.getVcodePara("VCode",phone,6);
				param.setmParserClassName(new  VerifyCodeParse());
				break;
			
		}
		RequestManager.getRequestData(getActivity(), createReqSuccessListener(), createMyReqErrorListener(), param);
	}

	private VerifyCode verifyCode = null;//验证码

	@Override
	public void handleRspSuccess(int requestType,Object obj) {
		switch (requetType){
			case 1:
				MSG_TOTAL_TIME = -1;
				JsonParserBase<VerifyCode> jsonParserBase1 = (JsonParserBase<VerifyCode>)obj;
				verifyCode = jsonParserBase1.getData();
//				forget_inputCode.setText(verifyCode !=null?verifyCode.getSmsCode():"");
				toasetUtil.showInfo("信息已发送!");
//				AlertDialogUtils.displayMyAlertChoice(mActivity,"验证码",verifyCode.getSmsCode()+"",null,null);
				break;
		}

	}

	@Override
	protected void failRespone() {
		super.failRespone();
		MSG_TOTAL_TIME = -11;

	}
	protected Response.ErrorListener createMyReqErrorListener() {
		super.createMyReqErrorListener();
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (!isDetached()) {
					Message msg = new Message();
					MSG_TOTAL_TIME = -11;
					Message message = new Message();
					message.what = MSG_UPDATE_TIME;
					timeHandler.removeMessages(MSG_UPDATE_TIME);
					timeHandler.sendMessageDelayed(message, 10000);
				}
			}
		};
	}
}
