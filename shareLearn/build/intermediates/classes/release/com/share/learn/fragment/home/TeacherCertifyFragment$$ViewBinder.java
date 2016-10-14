// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TeacherCertifyFragment$$ViewBinder<T extends com.share.learn.fragment.home.TeacherCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427398, "field 'idCertifyStatus'");
    target.idCertifyStatus = finder.castView(view, 2131427398, "field 'idCertifyStatus'");
    view = finder.findRequiredView(source, 2131427397, "field 'idCertifyRl'");
    target.idCertifyRl = finder.castView(view, 2131427397, "field 'idCertifyRl'");
    view = finder.findRequiredView(source, 2131427401, "field 'schoolCertifyStatus'");
    target.schoolCertifyStatus = finder.castView(view, 2131427401, "field 'schoolCertifyStatus'");
    view = finder.findRequiredView(source, 2131427399, "field 'chooseCertifyRl'");
    target.chooseCertifyRl = finder.castView(view, 2131427399, "field 'chooseCertifyRl'");
  }

  @Override public void unbind(T target) {
    target.idCertifyStatus = null;
    target.idCertifyRl = null;
    target.schoolCertifyStatus = null;
    target.chooseCertifyRl = null;
  }
}
