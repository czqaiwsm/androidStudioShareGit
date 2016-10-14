// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.center;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrederDetailFragment$$ViewBinder<T extends com.share.teacher.fragment.center.OrederDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493008, "field 'courseName'");
    target.courseName = finder.castView(view, 2131493008, "field 'courseName'");
    view = finder.findRequiredView(source, 2131493010, "field 'course_duration'");
    target.course_duration = finder.castView(view, 2131493010, "field 'course_duration'");
    view = finder.findRequiredView(source, 2131493016, "field 'count_time'");
    target.count_time = finder.castView(view, 2131493016, "field 'count_time'");
    view = finder.findRequiredView(source, 2131493018, "field 'dateTime'");
    target.dateTime = finder.castView(view, 2131493018, "field 'dateTime'");
    view = finder.findRequiredView(source, 2131493022, "field 'orderPrice'");
    target.orderPrice = finder.castView(view, 2131493022, "field 'orderPrice'");
    view = finder.findRequiredView(source, 2131493023, "field 'discount'");
    target.discount = finder.castView(view, 2131493023, "field 'discount'");
    view = finder.findRequiredView(source, 2131493024, "field 'payPrice'");
    target.payPrice = finder.castView(view, 2131493024, "field 'payPrice'");
    view = finder.findRequiredView(source, 2131493025, "field 'contact'");
    target.contact = finder.castView(view, 2131493025, "field 'contact'");
    view = finder.findRequiredView(source, 2131493026, "field 'buy'");
    target.buy = finder.castView(view, 2131493026, "field 'buy'");
    view = finder.findRequiredView(source, 2131492980, "field 'address'");
    target.address = finder.castView(view, 2131492980, "field 'address'");
    view = finder.findRequiredView(source, 2131493014, "field 'grade'");
    target.grade = finder.castView(view, 2131493014, "field 'grade'");
    view = finder.findRequiredView(source, 2131493020, "field 'mobile'");
    target.mobile = finder.castView(view, 2131493020, "field 'mobile'");
    view = finder.findRequiredView(source, 2131493019, "field 'mobile_layout'");
    target.mobile_layout = finder.castView(view, 2131493019, "field 'mobile_layout'");
  }

  @Override public void unbind(T target) {
    target.courseName = null;
    target.course_duration = null;
    target.count_time = null;
    target.dateTime = null;
    target.orderPrice = null;
    target.discount = null;
    target.payPrice = null;
    target.contact = null;
    target.buy = null;
    target.address = null;
    target.grade = null;
    target.mobile = null;
    target.mobile_layout = null;
  }
}
