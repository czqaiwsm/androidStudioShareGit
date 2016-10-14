// Generated code from Butter Knife. Do not modify!
package com.share.teacher.fragment.center;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WithdrawTypeFragment$$ViewBinder<T extends com.share.teacher.fragment.center.WithdrawTypeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493157, "field 'alipayBox'");
    target.alipayBox = finder.castView(view, 2131493157, "field 'alipayBox'");
    view = finder.findRequiredView(source, 2131493155, "field 'alipayLL'");
    target.alipayLL = finder.castView(view, 2131493155, "field 'alipayLL'");
    view = finder.findRequiredView(source, 2131493159, "field 'wxPayBox'");
    target.wxPayBox = finder.castView(view, 2131493159, "field 'wxPayBox'");
    view = finder.findRequiredView(source, 2131493158, "field 'wxPaylipayLL'");
    target.wxPaylipayLL = finder.castView(view, 2131493158, "field 'wxPaylipayLL'");
    view = finder.findRequiredView(source, 2131493161, "field 'bankBox'");
    target.bankBox = finder.castView(view, 2131493161, "field 'bankBox'");
    view = finder.findRequiredView(source, 2131493160, "field 'bankLL'");
    target.bankLL = finder.castView(view, 2131493160, "field 'bankLL'");
    view = finder.findRequiredView(source, 2131492951, "field 'rechargeQuery'");
    target.rechargeQuery = finder.castView(view, 2131492951, "field 'rechargeQuery'");
  }

  @Override public void unbind(T target) {
    target.alipayBox = null;
    target.alipayLL = null;
    target.wxPayBox = null;
    target.wxPaylipayLL = null;
    target.bankBox = null;
    target.bankLL = null;
    target.rechargeQuery = null;
  }
}
