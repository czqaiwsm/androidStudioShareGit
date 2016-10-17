package com.share.learn.bean;

import android.widget.TextView;

/**
 * Created by czq on 16/10/15.
 * 购买课程地址信息
 */
public class QueryClassInfo {

    private String address;
    private String studentName;
    private String grade;
    private String addressId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
