package com.share.learn.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.share.learn.R;
import com.share.learn.bean.AddressInfos;
import com.share.learn.utils.BaseApplication;

import java.util.List;

public class ChooseAddressAdapter extends BaseAdapter {
    private List<AddressInfos.AddressInfo> mItemList;
    private Context mContext;

    private String joniorId = "";
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItemList == null ? 0 : mItemList.size();
    }

    public ChooseAddressAdapter(Context context, List<AddressInfos.AddressInfo> items) {
        this.mContext = context;
        this.mItemList = items;
        joniorId = BaseApplication.getUserInfo().getGrade();

    }
    public ChooseAddressAdapter(Context context, List<AddressInfos.AddressInfo> items, String selectId) {
        this.mContext = context;
        this.mItemList = items;
        joniorId = selectId;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.choose_joinor_adapter,null);
            holder.joniorName = (TextView) convertView.findViewById(R.id.jonior);
            holder.joniorSelect = (CheckBox) convertView.findViewById(R.id.select_joinor);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddressInfos.AddressInfo idInfo = mItemList.get(position);
        if(idInfo != null){
           holder.joniorName.setText(idInfo.getAreaAddress()+idInfo.getDetailAddress());
            if(joniorId.equals(idInfo.getAddressId())){
                holder.joniorSelect.setChecked(true);
            }else {
                holder.joniorSelect.setChecked(false);
            }
        }
        return convertView;
    }

    class ViewHolder {
        private TextView joniorName;
        private CheckBox joniorSelect;
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



}
