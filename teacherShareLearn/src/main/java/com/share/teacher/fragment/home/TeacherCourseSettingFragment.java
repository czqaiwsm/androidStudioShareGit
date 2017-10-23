package com.share.teacher.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.reflect.TypeToken;
import com.share.teacher.R;
import com.share.teacher.activity.teacher.ChooseJoinorActivity;
import com.share.teacher.bean.CertifyStatus;
import com.share.teacher.bean.CourseInfo;
import com.share.teacher.bean.CourseInfoList;
import com.share.teacher.bean.DataMapConstants;
import com.share.teacher.bean.GradeInfo;
import com.share.teacher.bean.QueryStudentInfo;
import com.share.teacher.fragment.BaseFragment;
import com.share.teacher.help.RequestHelp;
import com.share.teacher.help.RequsetListener;
import com.share.teacher.utils.SmartToast;
import com.share.teacher.utils.URLConstants;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.net.inferface.IParser;
import com.volley.req.parser.JsonParserBase;
import com.volley.req.parser.ParserUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * @desc 教师首页
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class TeacherCourseSettingFragment extends BaseFragment implements View.OnClickListener ,RequsetListener,IParser{

    @Bind(R.id.idCertify_status)
    TextView subject;
    @Bind(R.id.idCertifyRl)
    RelativeLayout subjectRl;
    @Bind(R.id.schoolCertify_status)
    TextView jonior;
    @Bind(R.id.chooseCertifyRl)
    RelativeLayout joniorRl;

    private String joniorId = "";
    private String subjectId = "";

    private final int courseDesign = 1;
    private final int queryCourseAndGrade = 2;

    private int requestType = -1;

    private ArrayList<CourseInfoList> courseInfoLists = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_course_setting, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
        initView(view);
        requestTask(queryCourseAndGrade);
    }

    private void initView(View view) {
        subjectRl.setOnClickListener(this);
        joniorRl.setOnClickListener(this);
//        joniorRl.setClickable(false);
    }


    private void initTitle() {
        setTitleText(R.string.course_setting);
        setLeftHeadIcon(0);
        setHeaderRightText(R.string.sure, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTask(courseDesign);
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        ArrayList<String> tempArr = new ArrayList<>();
        switch (v.getId()) {
            case R.id.idCertifyRl://科目
                if(courseInfoLists != null && courseInfoLists.size() > 0){
                    intent = new Intent(mActivity, ChooseJoinorActivity.class);
                    intent.putExtra("joniorId",subjectId);
                    intent.setFlags(14);
//                    for(CourseInfoList list:courseInfoLists){
//                        tempArr.add(list.getCourseId()+"");
//                    }
                    intent.putExtra("list",courseInfoLists);
                    startActivityForResult(intent, URLConstants.CHOOSE_SUBJECT_REQUEST_CODE);
                }
                break;
            case R.id.chooseCertifyRl://年级
//                intent = new Intent(mActivity, ChooseJoinorActivity.class);
                if(!TextUtils.isEmpty(subjectId)
                        && courseInfoLists != null
                        && courseInfoLists.size() > 0){
                    intent = new Intent(mActivity, ChooseJoinorActivity.class);
                    intent.putExtra("joniorId",joniorId);
                    intent.setFlags(11);
                    for (CourseInfoList list:courseInfoLists){
                        if(subjectId.equalsIgnoreCase(list.getCourseId()+"")){
                            intent.putExtra("list",list.getGradeArr());
                            break;
                        }
                    }
                    startActivityForResult(intent, URLConstants.CHOOSE_JOINOR_REQUEST_CODE);
                }else if(TextUtils.isEmpty(subjectId)){
                    Toast.makeText(mActivity,"请先选着科目！",Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case URLConstants.CHOOSE_SUBJECT_REQUEST_CODE://科目
                    subjectId = data.getStringExtra(URLConstants.CHOOSE);
                    subject.setText(DataMapConstants.getCourse().get(subjectId));
                    if (!TextUtils.isEmpty(subjectId)) {
                        joniorRl.setClickable(true);
                    } else {
//                        joniorRl.setClickable(false);
                    }
                    break;
                case URLConstants.CHOOSE_JOINOR_REQUEST_CODE://年级
                    joniorId = data.getStringExtra(URLConstants.CHOOSE);
                    for (CourseInfoList list : courseInfoLists) {
                        if (subjectId.equalsIgnoreCase(list.getCourseId() + "")) {
                            for (GradeInfo gradeInfo : list.getGradeArr()) {
                                if (joniorId.equalsIgnoreCase(gradeInfo.getGradeId() + "")) {
                                    jonior.setText(gradeInfo.getGradeName());
                                    break;
                                }
                            }
                        }
                    }

                    break;
            }

        }

    }

    @Override
    protected void requestData(int requestType) {
        this.requestType = requestType;
        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        Map postParams = null;
        switch (requestType){
            case courseDesign:
                postParams = RequestHelp.getBaseParaMap("CourseDesign") ;
                postParams.put("courseId",subjectId);
                postParams.put("grade",joniorId);
                break;
            case queryCourseAndGrade:
                postParams = RequestHelp.getBaseParaMap("QueryCourseAndGrade") ;
                break;


        }
        RequestParam param = new RequestParam();
        param.setmParserClassName(this);
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(),createMyReqErrorListener(), param);

    }

    @Override
    public void handleRspSuccess(int requestType,Object obj) {
        switch (this.requestType){
            case courseDesign:
                JsonParserBase<CertifyStatus> jsonParserBase = (JsonParserBase<CertifyStatus>)obj;
                SmartToast.showText("设置成功");
                mActivity.setResult(Activity.RESULT_OK);
                mActivity.finish();
                break;
            case queryCourseAndGrade:
                JsonParserBase<ArrayList<CourseInfoList>> date =
                (JsonParserBase<ArrayList<CourseInfoList>>)obj;
                courseInfoLists = date.getData();
                break;

        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public Object fromJson(JSONObject object) {
        return null;
    }

    @Override
    public Object fromJson(String json) {

        JsonParserBase result = null;

        switch (requestType){
            case courseDesign:
                result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
                }.getType());
                if(URLConstants.SUCCESS_CODE.equals(result.getRespCode())){
                    return ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase<CertifyStatus>>() {
                    }.getType());
                }
                break;
            case queryCourseAndGrade:

                result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
                }.getType());
                if(URLConstants.SUCCESS_CODE.equals(result.getRespCode())){
                    return ParserUtil.fromJsonBase(json, new
                            TypeToken<JsonParserBase<ArrayList<CourseInfoList>>>() {
                    }.getType());
                }

                break;

        }



        return result;
    }
}