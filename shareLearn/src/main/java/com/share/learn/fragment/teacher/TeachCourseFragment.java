package com.share.learn.fragment.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.share.learn.R;
import com.share.learn.activity.center.PCenterInfoUserActivity;
import com.share.learn.activity.center.PurchaseCourseActivity;
import com.share.learn.activity.login.LoginActivity;
import com.share.learn.adapter.ContactAdpter;
import com.share.learn.adapter.TeacherCourseAdapter;
import com.share.learn.bean.CourseInfo;
import com.share.learn.bean.TeacherDetailInfo;
import com.share.learn.bean.UserInfo;
import com.share.learn.bean.msg.Message;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.fragment.center.PCenterInfoFragmentUser;
import com.share.learn.utils.AlertDialogUtils;
import com.share.learn.utils.BaseApplication;
import com.share.learn.view.CustomListView;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 老师信息-->课程
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class TeachCourseFragment extends BaseFragment {

    private CustomListView customListView = null;
    private List<CourseInfo> list = new ArrayList<CourseInfo>();
    TeacherCourseAdapter adapter;

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
                UserInfo userInfo = BaseApplication.getUserInfo();
                if(StringUtils.isEmpty(userInfo.getNickName()) ||
                        StringUtils.isEmpty(userInfo.getGrade())|| StringUtils.isEmpty(userInfo.getAddress())){
                    AlertDialogUtils.displayMyAlertChoice(getActivity(), "提示", "请完善个人资料及上课地址", new View.OnClickListener() {
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
                bundle.putSerializable("course",list.get(i-1));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

}