package com.share.learn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.share.learn.R;
import com.share.learn.activity.FeedBackDetailActivity;
import com.share.learn.bean.FeedBackDetail;
import com.share.learn.bean.FeedBackDetail;
import com.share.learn.bean.msg.Message;
import com.share.learn.utils.ImageLoaderUtil;
import com.share.learn.view.RoundImageView;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.List;

public class FeedBackAdpter extends BaseAdapter implements View.OnClickListener{
    private List<FeedBackDetail> mItemList;
    private Context mContext;

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItemList == null ? 0 : mItemList.size();

    }

    public FeedBackAdpter(Context context, List<FeedBackDetail> items) {
        this.mContext = context;
        this.mItemList = items;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mItemList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.feed_back_adapter, null);
            holder.tnTv = (TextView) convertView.findViewById(R.id.tnTv);
            holder.cnTv = (TextView) convertView.findViewById(R.id.cnTv);
            holder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
            holder.finishDataTv = (TextView) convertView.findViewById(R.id.finishDataTv);
            holder.cnFBBtn = (Button) convertView.findViewById(R.id.cnFBBtn);
            holder.teacherFBBtn = (Button) convertView.findViewById(R.id.teacherFBBtn);
            holder.cnFBBtn.setOnClickListener(this);
            holder.teacherFBBtn.setOnClickListener(this);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FeedBackDetail message = mItemList.get(position);
        if (message != null) {
            holder.tnTv.setText(message.getTeacherName());
            holder.cnTv.setText(message.getCourseName());
            holder.titleTv.setText(message.getTitle());
            holder.finishDataTv.setText(message.getFinishTime());
            holder.cnFBBtn.setTag(message.getConsultantFeedback());
            holder.teacherFBBtn.setTag(message.getTeacherFeedback());

        }
        return convertView;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, FeedBackDetailActivity.class);
        switch (v.getId()){
            case R.id.cnFBBtn:
                intent.putExtra("title","顾问反馈");
                intent.putExtra("content", (v.getTag() != null?v.getTag().toString():""));
                mContext.startActivity(intent);
                break;
            case R.id.teacherFBBtn:
                intent.putExtra("title","老师反馈");
                intent.putExtra("content", (v.getTag() != null?v.getTag().toString():""));
                mContext.startActivity(intent);

                break;
        }

    }

    class ViewHolder {
        protected TextView tnTv;
        protected TextView cnTv;
        protected TextView titleTv;
        protected TextView finishDataTv;
        protected Button cnFBBtn;
        protected Button teacherFBBtn;
    }
}
