package com.share.teacher.bean;

/**
 * @author czq
 * @desc 反馈列表
 * @date 16/4/12
 */
public class FeedBackDetail {

    private String id  ;//小订单id
    private String orderId  ;//订单id
    private String teacherName	;//老师姓名
    private String title	;//课时标题
    private String courseName	;//课目
    private String finishTime  ;//上课时间
    private String teacherFeedback	;//老师反馈
    private String consultantFeedback  ;//顾问反馈


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getTeacherFeedback() {
        return teacherFeedback;
    }

    public void setTeacherFeedback(String teacherFeedback) {
        this.teacherFeedback = teacherFeedback;
    }

    public String getConsultantFeedback() {
        return consultantFeedback;
    }

    public void setConsultantFeedback(String consultantFeedback) {
        this.consultantFeedback = consultantFeedback;
    }
}
