package com.share.learn.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.bean.AddressInfos;

import java.util.List;

public class ManageAddressAdapter extends BaseAdapter implements View.OnClickListener{
    private List<AddressInfos.AddressInfo> mItemList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public ManageAddressAdapter(Context context, List<AddressInfos.AddressInfo> items, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mItemList = items;
        this.onClickListener = onClickListener;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.manage_address_adapter,null);
            holder.joniorName = (TextView) convertView.findViewById(R.id.jonior);
            holder.editAddLl = (LinearLayout)convertView.findViewById(R.id.editAddLl);
            holder.delAddLl = (LinearLayout)convertView.findViewById(R.id.delAddLl);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddressInfos.AddressInfo idInfo = mItemList.get(position);
        if(idInfo != null){
            holder.joniorName.setText(idInfo.getAreaAddress()+idInfo.getDetailAddress());
            holder.editAddLl.setOnClickListener(this);
            holder.delAddLl.setOnClickListener(this);
            holder.editAddLl.setTag(idInfo);
            holder.delAddLl.setTag(idInfo);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView joniorName;
        private LinearLayout editAddLl;
        private LinearLayout delAddLl;

    }

    @Override
    public void onClick(View v) {
        if(onClickListener != null) onClickListener.onClick(v);
    }

    @Override
    public Object getItem(int position) {

        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


   public void addList(List<AddressInfos.AddressInfo> list){
       mItemList = list;
       notifyDataSetChanged();
   }


    public List<AddressInfos.AddressInfo> getmItemList(){
        return mItemList;
    }


}
