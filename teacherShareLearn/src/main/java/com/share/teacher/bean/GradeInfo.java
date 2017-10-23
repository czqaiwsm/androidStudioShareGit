package com.share.teacher.bean;


import java.io.Serializable;

/**
 * Created by czq on 2017/10/20.
 */

public class GradeInfo implements Serializable {

    private int gradeId ;
    private String gradeName = "";

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
