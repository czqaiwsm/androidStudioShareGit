// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SchoolCertifyFragment$$ViewBinder<T extends com.share.teacher.fragment.home.SchoolCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493122, "field 'degreeName'");
    target.degreeName = finder.castView(view, 2131493122, "field 'degreeName'");
    view = finder.findRequiredView(source, 2131493121, "field 'degreeRl'");
    target.degreeRl = finder.castView(view, 2131493121, "field 'degreeRl'");
    view = finder.findRequiredView(source, 2131493124, "field 'schoolName'");
    target.schoolName = finder.castView(view, 2131493124, "field 'schoolName'");
    view = finder.findRequiredView(source, 2131493123, "field 'schoolRl'");
    target.schoolRl = finder.castView(view, 2131493123, "field 'schoolRl'");
    view = finder.findRequiredView(source, 2131493128, "field 'majorName'");
    target.majorName = finder.castView(view, 2131493128, "field 'majorName'");
    view = finder.findRequiredView(source, 2131493126, "field 'faculty_name'");
    target.faculty_name = finder.castView(view, 2131493126, "field 'faculty_name'");
    view = finder.findRequiredView(source, 2131493125, "field 'majorRl'");
    target.majorRl = finder.castView(view, 2131493125, "field 'majorRl'");
    view = finder.findRequiredView(source, 2131492977, "field 'uploadLL'");
    target.uploadLL = finder.castView(view, 2131492977, "field 'uploadLL'");
    view = finder.findRequiredView(source, 2131493129, "field 'schoolCertifyImg'");
    target.schoolCertifyImg = finder.castView(view, 2131493129, "field 'schoolCertifyImg'");
  }

  @Override public void unbind(T target) {
    target.degreeName = null;
    target.degreeRl = null;
    target.schoolName = null;
    target.schoolRl = null;
    target.majorName = null;
    target.faculty_name = null;
    target.majorRl = null;
    target.uploadLL = null;
    target.schoolCertifyImg = null;
  }
}
