// Generated code from Butter Knife. Do not modify!
package com.share.learn.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ContactAdpter$ViewHolder$$ViewBinder<T extends com.share.learn.adapter.ContactAdpter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427375, "field 'headPhoto'");
    target.headPhoto = finder.castView(view, 2131427375, "field 'headPhoto'");
    view = finder.findRequiredView(source, 2131427376, "field 'name'");
    target.name = finder.castView(view, 2131427376, "field 'name'");
    view = finder.findRequiredView(source, 2131427377, "field 'time'");
    target.time = finder.castView(view, 2131427377, "field 'time'");
    view = finder.findRequiredView(source, 2131427382, "field 'msgContent'");
    target.msgContent = finder.castView(view, 2131427382, "field 'msgContent'");
  }

  @Override public void unbind(T target) {
    target.headPhoto = null;
    target.name = null;
    target.time = null;
    target.msgContent = null;
  }
}
