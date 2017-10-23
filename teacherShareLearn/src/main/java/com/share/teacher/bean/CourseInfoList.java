package com.share.teacher.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by czq on 2017/10/20.
 */

public class CourseInfoList implements Serializable{

    private int courseId ;
    private String courseName = "";
    private ArrayList<GradeInfo> gradeArr;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<GradeInfo> getGradeArr() {
        return gradeArr;
    }

    public void setGradeArr(ArrayList<GradeInfo> gradeArr) {
        this.gradeArr = gradeArr;
    }
}
