// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SchoolCertifyFragment$$ViewBinder<T extends com.share.learn.fragment.home.SchoolCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427567, "field 'degreeName'");
    target.degreeName = finder.castView(view, 2131427567, "field 'degreeName'");
    view = finder.findRequiredView(source, 2131427566, "field 'degreeRl'");
    target.degreeRl = finder.castView(view, 2131427566, "field 'degreeRl'");
    view = finder.findRequiredView(source, 2131427569, "field 'schoolName'");
    target.schoolName = finder.castView(view, 2131427569, "field 'schoolName'");
    view = finder.findRequiredView(source, 2131427568, "field 'schoolRl'");
    target.schoolRl = finder.castView(view, 2131427568, "field 'schoolRl'");
    view = finder.findRequiredView(source, 2131427571, "field 'majorName'");
    target.majorName = finder.castView(view, 2131427571, "field 'majorName'");
    view = finder.findRequiredView(source, 2131427570, "field 'majorRl'");
    target.majorRl = finder.castView(view, 2131427570, "field 'majorRl'");
    view = finder.findRequiredView(source, 2131427437, "field 'uploadLL'");
    target.uploadLL = finder.castView(view, 2131427437, "field 'uploadLL'");
    view = finder.findRequiredView(source, 2131427572, "field 'schoolCertifyImg'");
    target.schoolCertifyImg = finder.castView(view, 2131427572, "field 'schoolCertifyImg'");
  }

  @Override public void unbind(T target) {
    target.degreeName = null;
    target.degreeRl = null;
    target.schoolName = null;
    target.schoolRl = null;
    target.majorName = null;
    target.majorRl = null;
    target.uploadLL = null;
    target.schoolCertifyImg = null;
  }
}
