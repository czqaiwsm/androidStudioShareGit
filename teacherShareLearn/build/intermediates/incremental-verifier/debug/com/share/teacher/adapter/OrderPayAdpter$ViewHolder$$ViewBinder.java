// Generated code from Butter Knife. Do not modify!
package com.share.teacher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrderPayAdpter$ViewHolder$$ViewBinder<T extends com.share.teacher.adapter.OrderPayAdpter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492973, "field 'name'");
    target.name = finder.castView(view, 2131492973, "field 'name'");
    view = finder.findRequiredView(source, 2131493191, "field 'payStatus'");
    target.payStatus = finder.castView(view, 2131493191, "field 'payStatus'");
    view = finder.findRequiredView(source, 2131493008, "field 'courseName'");
    target.courseName = finder.castView(view, 2131493008, "field 'courseName'");
    view = finder.findRequiredView(source, 2131492920, "field 'price'");
    target.price = finder.castView(view, 2131492920, "field 'price'");
    view = finder.findRequiredView(source, 2131493192, "field 'view'");
    target.view = view;
    view = finder.findRequiredView(source, 2131493193, "field 'timeDetail'");
    target.timeDetail = finder.castView(view, 2131493193, "field 'timeDetail'");
    view = finder.findRequiredView(source, 2131493012, "field 'left_tv'");
    target.left_tv = finder.castView(view, 2131493012, "field 'left_tv'");
    view = finder.findRequiredView(source, 2131493194, "field 'right_tv'");
    target.right_tv = finder.castView(view, 2131493194, "field 'right_tv'");
  }

  @Override public void unbind(T target) {
    target.name = null;
    target.payStatus = null;
    target.courseName = null;
    target.price = null;
    target.view = null;
    target.timeDetail = null;
    target.left_tv = null;
    target.right_tv = null;
  }
}
