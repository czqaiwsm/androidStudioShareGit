package com.share.teacher.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by czq on 2017/9/5.
 */

public class FeedList implements Serializable {

    private ArrayList<FeedBackDetail>  dataList ;

    public ArrayList<FeedBackDetail> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<FeedBackDetail> dataList) {
        this.dataList = dataList;
    }
}
