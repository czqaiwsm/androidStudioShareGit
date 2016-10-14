// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TeacherHomePageFragment$$ViewBinder<T extends com.share.teacher.fragment.TeacherHomePageFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493141, "field 'courseSettingImg'");
    target.courseSettingImg = finder.castView(view, 2131493141, "field 'courseSettingImg'");
    view = finder.findRequiredView(source, 2131493142, "field 'certifySettingImg'");
    target.certifySettingImg = finder.castView(view, 2131493142, "field 'certifySettingImg'");
    view = finder.findRequiredView(source, 2131493143, "field 'myOrder'");
    target.myOrder = finder.castView(view, 2131493143, "field 'myOrder'");
    view = finder.findRequiredView(source, 2131493144, "field 'myBalance'");
    target.myBalance = finder.castView(view, 2131493144, "field 'myBalance'");
  }

  @Override public void unbind(T target) {
    target.courseSettingImg = null;
    target.certifySettingImg = null;
    target.myOrder = null;
    target.myBalance = null;
  }
}
