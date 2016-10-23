package com.share.learn.fragment.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.share.learn.R;
import com.share.learn.activity.center.PCenterInfoUserActivity;
import com.share.learn.activity.center.PurchaseCourseActivity;
import com.share.learn.activity.login.LoginActivity;
import com.share.learn.adapter.ContactAdpter;
import com.share.learn.adapter.TeacherCourseAdapter;
import com.share.learn.bean.CourseInfo;
import com.share.learn.bean.PayCourseInfo;
import com.share.learn.bean.PayInfo;
import com.share.learn.bean.QueryClassInfo;
import com.share.learn.bean.TeacherDetailInfo;
import com.share.learn.bean.UserInfo;
import com.share.learn.bean.msg.Message;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.fragment.center.PCenterInfoFragmentUser;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.BaseParse;
import com.share.learn.parse.PayCourseInfoParse;
import com.share.learn.parse.QueryClassParse;
import com.share.learn.utils.AlertDialogUtils;
import com.share.learn.utils.BaseApplication;
import com.share.learn.utils.PayUtil;
import com.share.learn.utils.SmartToast;
import com.share.learn.utils.URLConstants;
import com.share.learn.view.CustomListView;
import com.ta.utdid2.android.utils.StringUtils;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 老师信息-->课程
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class TeachCourseFragment extends BaseFragment implements RequsetListener {

    private CustomListView customListView = null;
    private List<CourseInfo> list = new ArrayList<CourseInfo>();
    TeacherCourseAdapter adapter;
    private QueryClassInfo queryClassInfo = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null ){
            list = (ArrayList<CourseInfo>) bundle.getSerializable("courses");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view){

        customListView = (CustomListView)view.findViewById(R.id.callListView);

        customListView.setCanLoadMore(false);
        customListView.setCanRefresh(false);
         adapter = new TeacherCourseAdapter(mActivity, list);
        customListView.setAdapter(adapter);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!BaseApplication.isLogin()){
//                    toasetUtil.showInfo("请先登录!");
                    toClassActivity(TeachCourseFragment.this, LoginActivity.class.getName());
                    return;
                }

                requestTask(i);

            }
        });
    }


    /**
     * 请求 用户信息
     */
    @Override
    public void requestData(int requestType) {
        // TODO Auto-generated method stub
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = null;
        RequestParam param = new RequestParam();
                postParams = RequestHelp.getBaseParaMap("QueryClassInfo");
                param.setmParserClassName(new QueryClassParse());
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(requestType), createMyReqErrorListener(), param);
    }


    @Override
    public void handleRspSuccess(int requestType,Object obj) {
//                if(queryClassInfo != null){
//                    if(!TextUtils.isEmpty(queryClassInfo.getAddress())){
//                        address.setText(queryClassInfo.getAddress());
//                    }
//                }


//        UserInfo userInfo = BaseApplication.getUserInfo();
        queryClassInfo =((JsonParserBase<QueryClassInfo>)obj).getData();
        if(queryClassInfo == null || StringUtils.isEmpty(queryClassInfo.getStudentName()) ||
            StringUtils.isEmpty(queryClassInfo.getGrade())|| StringUtils.isEmpty(queryClassInfo.getAddress())){
            AlertDialogUtils.displayMyAlertChoice(getActivity(), "提示", "请您完善个人姓名、年级和上课地址信息，然后再购买课程", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity,PCenterInfoUserActivity.class);
                    startActivity(intent);
                }
            },null);

            return;
        }


        Intent intent = new Intent(mActivity,PurchaseCourseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("course",list.get(requestType-1));
        intent.putExtra("bundle",bundle);
        intent.putExtra("address",queryClassInfo);
        startActivity(intent);
    }

}