// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TeacherCourseSettingFragment$$ViewBinder<T extends com.share.teacher.fragment.home.TeacherCourseSettingFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492938, "field 'subject'");
    target.subject = finder.castView(view, 2131492938, "field 'subject'");
    view = finder.findRequiredView(source, 2131492937, "field 'subjectRl'");
    target.subjectRl = finder.castView(view, 2131492937, "field 'subjectRl'");
    view = finder.findRequiredView(source, 2131492941, "field 'jonior'");
    target.jonior = finder.castView(view, 2131492941, "field 'jonior'");
    view = finder.findRequiredView(source, 2131492939, "field 'joniorRl'");
    target.joniorRl = finder.castView(view, 2131492939, "field 'joniorRl'");
  }

  @Override public void unbind(T target) {
    target.subject = null;
    target.subjectRl = null;
    target.jonior = null;
    target.joniorRl = null;
  }
}
