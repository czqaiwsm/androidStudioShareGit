package com.share.learn.fragment.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.share.learn.R;
import com.share.learn.adapter.SmallOrderAdapter;
import com.share.learn.bean.SmallOrder;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.utils.AlertDialogUtils;
import com.share.learn.utils.SmartToast;
import com.share.learn.utils.URLConstants;
import com.share.learn.view.CustomListView;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.net.inferface.IParser;
import com.volley.req.parser.JsonParserBase;
import com.volley.req.parser.ParserUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 小订单列表
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class SmallOrderFragment extends BaseFragment implements View.OnClickListener,RequsetListener,IParser {

    private CustomListView customListView = null;
    private List<SmallOrder> list = new ArrayList<SmallOrder>();
    private SmallOrderAdapter adapter;
    private String orderId = "";
    private String peroidId = "";
    private boolean isFinish = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = mActivity.getIntent();
        if(intent != null && intent.hasExtra("orderId")){
            orderId = intent.getStringExtra("orderId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_header,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTitleView();
        requestTask(1);
    }

    private void initTitleView() {
        setLeftHeadIcon(0);
        setTitleText("订单明细");
        setLeftHeadIcon(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinish) mActivity.setResult(Activity.RESULT_OK);
                mActivity.finish();
            }
        });
    }

    private void initView(View view){

        customListView = (CustomListView)view.findViewById(R.id.callListView);

        customListView.setCanLoadMore(false);
        customListView.setCanRefresh(false);
         adapter = new SmallOrderAdapter(mActivity, list,this);
        customListView.setAdapter(adapter);
    }

    /**
     * 请求 用户信息
     */
    @Override
    public void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = null;
        RequestParam param = new RequestParam();

        switch (requestType){
            case 1:
                postParams = RequestHelp.getBaseParaMap("OrderDetailList");
                postParams.put("orderId",orderId);
                param.setmParserClassName(this);
                break;
            case 2:
                postParams = RequestHelp.getBaseParaMap("FinishCourse");
                postParams.put("orderId",orderId);
                postParams.put("periodId",peroidId);
                param.setmParserClassName(this);
                break;
        }
//        param.setmParserClassName(TeacherDetailParse.class.getName());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(requestType), createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType,Object obj)  {
        switch (requestType){
            case 1:
                JsonParserBase<OrderList> jsonParserBase = (JsonParserBase<OrderList>)obj;
                adapter.getmItemList().addAll(jsonParserBase.getData().getDataList());
                adapter.notifyDataSetChanged();
                break;
            case 2:
                for(SmallOrder smallOrder:adapter.getmItemList()){
                    if(peroidId.equals(smallOrder.getPeriodId()) && orderId.equals(smallOrder.getOrderId())){
                        smallOrder.setStatus("2");
                        adapter.notifyDataSetChanged();
                        SmartToast.showText("订单已完成!");
                        isFinish = true;
                        break;
                    }
                }
                break;

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finishTv:
                String str = v.getTag().toString();
                orderId = str.split(";")[0];
                peroidId = str.split(";")[1];

                AlertDialogUtils.displayMyAlertChoice(mActivity, "提示", "确定完成此课时?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestTask(2);
                    }
                },null);
                break;
        }
    }

    @Override
    public Object fromJson(JSONObject object) {
        return null;
    }

    @Override
    public Object fromJson(String json) {
        JsonParserBase result = null;
        if(TextUtils.isEmpty(peroidId)){
            result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
            }.getType());
            if(URLConstants.SUCCESS_CODE.equals(result.getRespCode())){
                return ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase<OrderList>>() {
                }.getType());
            }
        }else {
            result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
            }.getType());
        }

        return result;
    }

    public class OrderList{

        ArrayList<SmallOrder> dataList = null;

        public ArrayList<SmallOrder> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<SmallOrder> dataList) {
            this.dataList = dataList;
        }
    }

}