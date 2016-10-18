package com.share.learn.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.bean.SmallOrder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SmallOrderAdapter extends BaseAdapter {
    private List<SmallOrder> mItemList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItemList == null ? 0 : mItemList.size();
    }

    public SmallOrderAdapter(Context context, List<SmallOrder> items, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mItemList = items;
        this.onClickListener = onClickListener;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_small_order, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SmallOrder map = mItemList.get(position);
        if (map != null) {
            holder.teacherTv.setText(map.getTeacherName());
            holder.courseTv.setText(map.getCourseName());
            holder.courseHourTv.setText(map.getTitle());
            holder.finishTv.setOnClickListener(onClickListener);
            holder.finishTv.setTag(map.getOrderId()+";"+map.getPeriodId());
            holder.finishTv.setBackgroundColor(mContext.getResources().getColor(R.color.login_normal));
            if("2".equals(map.getStatus())){
                holder.finishTv.setOnClickListener(null);
                holder.finishTv.setBackgroundColor(mContext.getResources().getColor(R.color.color_small_gray));
            }
        }
        return convertView;
    }


    @Override
    public Object getItem(int position) {

        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<SmallOrder> getmItemList(){
        return mItemList;
    }


    class ViewHolder {
        @Bind(R.id.teacherTv)
        TextView teacherTv;
        @Bind(R.id.courseTv)
        TextView courseTv;
        @Bind(R.id.courseHourTv)
        TextView courseHourTv;
        @Bind(R.id.finishTv)
        TextView finishTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
