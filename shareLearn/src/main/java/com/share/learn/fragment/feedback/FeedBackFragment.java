package com.share.learn.fragment.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.adapter.FeedBackAdpter;
import com.share.learn.bean.FeedBackDetail;
import com.share.learn.bean.FeedList;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.help.PullRefreshStatus;
import com.share.learn.help.RequestHelp;
import com.share.learn.help.RequsetListener;
import com.share.learn.parse.FeeBackParse;
import com.share.learn.utils.URLConstants;
import com.share.learn.utils.WaitLayer;
import com.share.learn.view.CustomListView;
import com.volley.req.net.HttpURL;
import com.volley.req.net.RequestManager;
import com.volley.req.net.RequestParam;
import com.volley.req.parser.JsonParserBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 反馈列表
 * @creator caozhiqing
 * @data 2016/3/10
 */
public class FeedBackFragment extends BaseFragment implements RequsetListener{

    private CustomListView customListView = null;
    private List<FeedBackDetail> list = new ArrayList<>();
    private FeedBackAdpter adapter;
    private TextView noData;
    private TextView title;

    private boolean isPrepare = false;
    private boolean isHiden = true;

    private PullRefreshStatus status = PullRefreshStatus.NORMAL;

    private int flag = 1; //1 list 2删除

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setLoadingDilog(WaitLayer.DialogType.NOT_NOMAL);
        isPrepare = true;
        onLoadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHiden = hidden;
        if(isPrepare && hidden){
            dismissLoadingDilog();
        }
        onLoadData();

    }

    private void onLoadData(){

        if(isPrepare && !isHiden){
            flag = 1;
            requestData(0);
        }

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            isVisible = true;
//        } else {
//            isVisible = false;
//            if (isPrepare) {
//                dismissLoadingDilog();
//            }
//        }
//
//        onLazyLoad();
//    }

    private void initView(View view) {

        customListView = (CustomListView) view.findViewById(R.id.callListView);
        noData = (TextView) view.findViewById(R.id.noData);
        title = (TextView) view.findViewById(R.id.header_title);
        title.setText("课时反馈");
        customListView.setCanRefresh(true);
        customListView.setCanLoadMore(false);
        adapter = new FeedBackAdpter(mActivity, list);
        customListView.setAdapter(adapter);

        customListView.setOnRefreshListener(new CustomListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                flag = 1;
                requestData(0);
            }
        });
    }


    @Override
    protected void requestData(int requestType) {

        HttpURL url = new HttpURL();
        url.setmBaseUrl(URLConstants.BASE_URL);
        RequestParam param = new RequestParam();

        Map postParams = null;
        if (flag == 1) {
            postParams = RequestHelp.getBaseParaMap("FeedbackList");
            param.setmParserClassName(new FeeBackParse());

        }
        param.setmPostarams(postParams);
        param.setmHttpURL(url);
        param.setPostRequestMethod();
        RequestManager.getRequestData(getActivity(), createReqSuccessListener(), createMyReqErrorListener(), param);
    }

    @Override
    public void handleRspSuccess(int requestType,Object obj) {
        if (flag == 1) {
            customListView.onRefreshComplete();
            customListView.onLoadMoreComplete();
            if (!isHiden) {
                JsonParserBase<FeedList> jsonParserBase = (JsonParserBase<FeedList>) obj;
                ArrayList<FeedBackDetail> chooseTeachBean = jsonParserBase.getData().getDataList();
                if (chooseTeachBean != null) {
                    refresh(chooseTeachBean);
                }

            }
        }

//        else if (flag == 2) {
//            int positon = -1;
//            FeedBackDetail selectMsg = null;
//            for (FeedBackDetail FeedBackDetail : list) {
//                if (TextUtils.equals(receiverId, FeedBackDetail.getReceiverId()) &&
//                        TextUtils.equals(senderId, FeedBackDetail.getSenderId())) {
//                    selectMsg = FeedBackDetail;
//                    break;
//                }
//            }
//
//            if (selectMsg != null) {
//                list.remove(selectMsg);
//            }
//            adapter.notifyDataSetChanged();
//            toasetUtil.showSuccess("删除成功!");
//
//        }
    }

    @Override
    protected void failRespone() {
        super.failRespone();
        customListView.onRefreshComplete();
        customListView.onLoadMoreComplete();
        switch (status) {
            case PULL_REFRESH:
                break;
            default:
                break;
        }
    }

    @Override
    protected void errorRespone() {
        super.errorRespone();
        failRespone();
    }

    /**
     * 页数为1时使用
     *
     * @param teacherInfos
     */
    private void refresh(ArrayList<FeedBackDetail> teacherInfos) {
        if (teacherInfos == null || teacherInfos.size() == 0) {//显示无数据
            if (list.size() == 0) {
                customListView.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
        } else {
            noData.setVisibility(View.GONE);
            customListView.setVisibility(View.VISIBLE);
            list.clear();
            list.addAll(teacherInfos);
            adapter.notifyDataSetChanged();

        }
    }
}