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
import com.share.learn.activity.teacher.AddAddressActivity;
import com.share.learn.adapter.ChooseAddressAdapter;
import com.share.learn.adapter.ManageAddressAdapter;
import com.share.learn.bean.AddressInfos;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
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
public class ManageAddressFragment extends BaseFragment implements RequsetListener ,IParser,View.OnClickListener{

    private CustomListView customListView = null;
    private List<AddressInfos.AddressInfo> list = new ArrayList<>();
    private ManageAddressAdapter adapter;
    private AddressInfos.AddressInfo addressInfo = null;
    private TextView noData ;
    private TextView addRessTv ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        requestTask(0);
    }

    private void initTitle(){
        setTitleText("地址管理");
        setLeftHeadIcon(0);
    }

    private AddressInfos.AddressInfo idInfo = null;
    private void initView(View view){

        customListView = (CustomListView)view.findViewById(R.id.callListView);
        noData = (TextView)view.findViewById(R.id.noData);
        addRessTv = (TextView)view.findViewById(R.id.addRessTv);
        addRessTv.setOnClickListener(this);
        addRessTv.setVisibility(View.VISIBLE);
        customListView.setCanLoadMore(false);
        customListView.setCanRefresh(false);

        adapter = new ManageAddressAdapter(mActivity, list,this);
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
        Map postParams = null;
        RequestParam param = null;
        switch (requestType){
            case 0:
                postParams = RequestHelp.getBaseParaMap("QueryAddressList");
                param = new RequestParam();
                param.setmParserClassName(this);
                param.setmPostarams(postParams);
                break;
            case 2:
                postParams = RequestHelp.getBaseParaMap("DelAddress");//删除地址
                postParams.put("addressId",addressInfo.getAddressId());
                param = new RequestParam();
                param.setmParserClassName(this);
                param.setmPostarams(postParams);
                break;

        }
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(requestType), createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType,Object obj) {
        switch (requestType){
            case 0:
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
                break;
            case 2:
                for (AddressInfos.AddressInfo add:adapter.getmItemList()){
                    if(add.getAddressId().equals(addressInfo.getAddressId())){
                        list.remove(add);
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }
                break;
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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.delAddLl:
                addressInfo = (AddressInfos.AddressInfo) v.getTag();
                requestTask(2);
                break;
            case R.id.editAddLl:
                addressInfo = (AddressInfos.AddressInfo) v.getTag();
                intent = new Intent(mActivity, AddAddressActivity.class);
                intent.putExtra("editType",2);
                intent.putExtra("address",addressInfo);
                startActivityForResult(intent,100);
//                addressInfo = (AddressInfos.AddressInfo) v.getTag();
//                requestData(1);
                break;
            case R.id.addRessTv:
                intent = new Intent(mActivity, AddAddressActivity.class);
                intent.putExtra("editType",1);
                startActivityForResult(intent,100);
                break;

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 100) requestTask(0);
    }
}