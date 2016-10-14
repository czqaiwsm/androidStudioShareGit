// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TeacherCertifyFragment$$ViewBinder<T extends com.share.teacher.fragment.home.TeacherCertifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492938, "field 'idCertifyStatus'");
    target.idCertifyStatus = finder.castView(view, 2131492938, "field 'idCertifyStatus'");
    view = finder.findRequiredView(source, 2131492937, "field 'idCertifyRl'");
    target.idCertifyRl = finder.castView(view, 2131492937, "field 'idCertifyRl'");
    view = finder.findRequiredView(source, 2131492941, "field 'schoolCertifyStatus'");
    target.schoolCertifyStatus = finder.castView(view, 2131492941, "field 'schoolCertifyStatus'");
    view = finder.findRequiredView(source, 2131492939, "field 'chooseCertifyRl'");
    target.chooseCertifyRl = finder.castView(view, 2131492939, "field 'chooseCertifyRl'");
  }

  @Override public void unbind(T target) {
    target.idCertifyStatus = null;
    target.idCertifyRl = null;
    target.schoolCertifyStatus = null;
    target.chooseCertifyRl = null;
  }
}
