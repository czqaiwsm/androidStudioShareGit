package com.share.learn.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.share.learn.R;
import com.share.learn.activity.teacher.ManageAddressActivity;
import com.share.learn.adapter.ChooseAddressAdapter;
import com.share.learn.bean.AddressInfos;
import com.share.learn.bean.BalanceInfo;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.HomePageBannerParse;
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
 * @desc 筛选界面
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class ChooseAddressFragment extends BaseFragment implements RequsetListener ,IParser{

    private CustomListView customListView = null;
    private List<AddressInfos.AddressInfo> list = new ArrayList<>();
    private ChooseAddressAdapter adapter;
    private String selectId = "";
    private TextView noData ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = mActivity.getIntent();
        if(intent != null){
            if(intent.hasExtra("joniorId"))
            selectId =intent.getStringExtra("joniorId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_list,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTitle();
        requestData(0);
    }

    private void initTitle(){
        setTitleText(R.string.choose_add);
        setLeftHeadIcon(0);
        setHeaderRightText("管理", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,ManageAddressActivity.class);
                startActivity(intent);
            }
        });

    }

    private AddressInfos.AddressInfo idInfo = null;
    private void initView(View view){

        customListView = (CustomListView)view.findViewById(R.id.callListView);
        noData = (TextView)view.findViewById(R.id.noData);
        customListView.setCanLoadMore(false);
        customListView.setCanRefresh(false);

        adapter = new ChooseAddressAdapter(mActivity, list,selectId);
        customListView.setAdapter(adapter);
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent();
                idInfo  = (AddressInfos.AddressInfo) arg0.getItemAtPosition(arg2);
//                switch (joniorType){
//                    case JONIOR:
                        intent.putExtra(URLConstants.CHOOSE, idInfo.getAddressId());
//                        break;
//                }
                mActivity.setResult(Activity.RESULT_OK,intent);
                mActivity.finish();

            }
        });
    }


    @Override
    protected void requestData(int requestType) {
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = RequestHelp.getBaseParaMap("QueryAddressList");
        RequestParam param = new RequestParam();
        param.setmParserClassName(this);
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(), createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType,Object obj) {
        JsonParserBase<AddressInfos> jsonParserBase = (JsonParserBase<AddressInfos>)obj;
        AddressInfos addressInfoses = jsonParserBase.getData();
        if(addressInfoses != null && addressInfoses.getAddressList() != null && addressInfoses.getAddressList().size()>0){
            customListView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            adapter.addList(addressInfoses.getAddressList());
        }else {
            customListView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
            noData.setText("暂无地址,请添加!");
        }
    }

    @Override
    public Object fromJson(JSONObject object) {
        return null;
    }

    @Override
    public Object fromJson(String json) {
        JsonParserBase result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
        }.getType());
        if(URLConstants.SUCCESS_CODE.equals(result.getRespCode())){
            return ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase<AddressInfos>>() {
            }.getType());
        }
        return result;
    }
}