// Generated code from Butter Knife. Do not modify!
package com.share.teacher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailAdapter$ViewHolder$$ViewBinder<T extends com.share.teacher.adapter.DetailAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492924, "field 'detailContent'");
    target.detailContent = finder.castView(view, 2131492924, "field 'detailContent'");
    view = finder.findRequiredView(source, 2131492916, "field 'time'");
    target.time = finder.castView(view, 2131492916, "field 'time'");
    view = finder.findRequiredView(source, 2131492920, "field 'price'");
    target.price = finder.castView(view, 2131492920, "field 'price'");
  }

  @Override public void unbind(T target) {
    target.detailContent = null;
    target.time = null;
    target.price = null;
  }
}
