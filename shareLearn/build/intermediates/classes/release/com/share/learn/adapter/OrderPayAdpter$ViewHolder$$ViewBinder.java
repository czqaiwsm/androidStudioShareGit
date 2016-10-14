// Generated code from Butter Knife. Do not modify!
package com.share.learn.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrderPayAdpter$ViewHolder$$ViewBinder<T extends com.share.learn.adapter.OrderPayAdpter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427433, "field 'name'");
    target.name = finder.castView(view, 2131427433, "field 'name'");
    view = finder.findRequiredView(source, 2131427629, "field 'payStatus'");
    target.payStatus = finder.castView(view, 2131427629, "field 'payStatus'");
    view = finder.findRequiredView(source, 2131427468, "field 'courseName'");
    target.courseName = finder.castView(view, 2131427468, "field 'courseName'");
    view = finder.findRequiredView(source, 2131427381, "field 'price'");
    target.price = finder.castView(view, 2131427381, "field 'price'");
    view = finder.findRequiredView(source, 2131427377, "field 'time'");
    target.time = finder.castView(view, 2131427377, "field 'time'");
    view = finder.findRequiredView(source, 2131427630, "field 'view'");
    target.view = view;
    view = finder.findRequiredView(source, 2131427631, "field 'timeDetail'");
    target.timeDetail = finder.castView(view, 2131427631, "field 'timeDetail'");
    view = finder.findRequiredView(source, 2131427472, "field 'left_tv'");
    target.left_tv = finder.castView(view, 2131427472, "field 'left_tv'");
    view = finder.findRequiredView(source, 2131427632, "field 'right_tv'");
    target.right_tv = finder.castView(view, 2131427632, "field 'right_tv'");
  }

  @Override public void unbind(T target) {
    target.name = null;
    target.payStatus = null;
    target.courseName = null;
    target.price = null;
    target.time = null;
    target.view = null;
    target.timeDetail = null;
    target.left_tv = null;
    target.right_tv = null;
  }
}
