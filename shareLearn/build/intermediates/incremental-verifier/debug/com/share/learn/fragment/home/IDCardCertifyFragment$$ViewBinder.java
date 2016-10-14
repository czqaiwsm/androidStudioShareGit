// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IDCardCertifyFragment$$ViewBinder<T extends com.share.learn.fragment.home.IDCardCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427434, "field 'nameEdit'");
    target.nameEdit = finder.castView(view, 2131427434, "field 'nameEdit'");
    view = finder.findRequiredView(source, 2131427436, "field 'idCardEdit'");
    target.idCardEdit = finder.castView(view, 2131427436, "field 'idCardEdit'");
    view = finder.findRequiredView(source, 2131427437, "field 'uploadLL'");
    target.uploadLL = finder.castView(view, 2131427437, "field 'uploadLL'");
    view = finder.findRequiredView(source, 2131427438, "field 'idImg'");
    target.idImg = finder.castView(view, 2131427438, "field 'idImg'");
  }

  @Override public void unbind(T target) {
    target.nameEdit = null;
    target.idCardEdit = null;
    target.uploadLL = null;
    target.idImg = null;
  }
}
