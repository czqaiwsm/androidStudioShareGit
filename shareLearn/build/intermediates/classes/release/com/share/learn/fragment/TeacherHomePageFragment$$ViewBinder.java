// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TeacherHomePageFragment$$ViewBinder<T extends com.share.learn.fragment.TeacherHomePageFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427586, "field 'courseSettingImg'");
    target.courseSettingImg = finder.castView(view, 2131427586, "field 'courseSettingImg'");
    view = finder.findRequiredView(source, 2131427587, "field 'certifySettingImg'");
    target.certifySettingImg = finder.castView(view, 2131427587, "field 'certifySettingImg'");
    view = finder.findRequiredView(source, 2131427588, "field 'myOrder'");
    target.myOrder = finder.castView(view, 2131427588, "field 'myOrder'");
    view = finder.findRequiredView(source, 2131427589, "field 'myBalance'");
    target.myBalance = finder.castView(view, 2131427589, "field 'myBalance'");
  }

  @Override public void unbind(T target) {
    target.courseSettingImg = null;
    target.certifySettingImg = null;
    target.myOrder = null;
    target.myBalance = null;
  }
}
