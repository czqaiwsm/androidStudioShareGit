package com.share.teacher.fragment.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.share.teacher.R;
import com.share.teacher.fragment.BaseFragment;
import com.share.teacher.help.RequestHelp;
import com.share.teacher.help.RequsetListener;
import com.share.teacher.parse.BaseParse;
import com.share.teacher.parse.TeacherDetailParse;
import com.share.teacher.utils.BaseApplication;
import com.share.teacher.utils.URLConstants;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;

import java.util.Map;

/**
 * 钱包
 *
 * @author czq
 * @time 2015年9月28日上午11:44:26
 */
public class FeedBackFragment extends BaseFragment implements OnClickListener, RequsetListener {

    private EditText rechargePrice;
    private TextView rechareQuery;
    private int type = 1;//1   2反馈列表
    private String id = "";
    private String orderId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("type")) {
            type = intent.getIntExtra("type", 1);
            String content = intent.getStringExtra("id");
            id = content.split("-")[0];
            orderId = content.split("-")[1];
        }
        initTitleView();
        initView(view);
    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText(R.string.feed_back_content);
        if (type == 2) {
            setTitleText("我的建议");
        }
        setLeftHeadIcon(0);
    }

    private void initView(View v) {
        rechargePrice = (EditText) v.findViewById(R.id.recharge_price);
        rechareQuery = (TextView) v.findViewById(R.id.recharge_query);
        rechareQuery.setOnClickListener(this);
        if (type == 2) {
            rechargePrice.setHint("请填写您对该学生的学习建议，不得低于25字！");
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.recharge_query:
                if (!TextUtils.isEmpty(rechargePrice.getText())) {
                    if(type == 2 && TextUtils.getTrimmedLength(rechargePrice.getText().toString()
                    ) < 25){
                        toasetUtil.showInfo("反馈类容过短!");
                        return;
                    }
                    requestTask();
                } else {
                    toasetUtil.showInfo("请填写反馈类容!");
                }
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
        RequestParam param = new RequestParam();
        Map postParams = null;
        if(type !=2 ){
            postParams = RequestHelp.getBaseParaMap("FeedBackPost");//关注
        }else {
            postParams = RequestHelp.getBaseParaMap("TeacherFeedback");//列表反馈
            postParams.put("id", id);
            postParams.put("orderId", orderId);

        }
        postParams.put("content", rechargePrice.getText().toString());
        param.setmParserClassName(new BaseParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(),
                createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType, Object obj) {
        Toast.makeText(BaseApplication.getInstance(), "感谢您的反馈!", Toast.LENGTH_LONG).show();
        mActivity.setResult(100);
        mActivity.finish();
    }


}
