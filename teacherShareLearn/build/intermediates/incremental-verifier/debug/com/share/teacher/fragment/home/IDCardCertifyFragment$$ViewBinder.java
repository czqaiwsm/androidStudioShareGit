// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IDCardCertifyFragment$$ViewBinder<T extends com.share.teacher.fragment.home.IDCardCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492974, "field 'nameEdit'");
    target.nameEdit = finder.castView(view, 2131492974, "field 'nameEdit'");
    view = finder.findRequiredView(source, 2131492976, "field 'idCardEdit'");
    target.idCardEdit = finder.castView(view, 2131492976, "field 'idCardEdit'");
    view = finder.findRequiredView(source, 2131492977, "field 'uploadLL'");
    target.uploadLL = finder.castView(view, 2131492977, "field 'uploadLL'");
    view = finder.findRequiredView(source, 2131492978, "field 'idImg'");
    target.idImg = finder.castView(view, 2131492978, "field 'idImg'");
  }

  @Override public void unbind(T target) {
    target.nameEdit = null;
    target.idCardEdit = null;
    target.uploadLL = null;
    target.idImg = null;
  }
}
