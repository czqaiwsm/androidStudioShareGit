// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.center;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WithdrawTypeFragment$$ViewBinder<T extends com.share.learn.fragment.center.WithdrawTypeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427600, "field 'alipayBox'");
    target.alipayBox = finder.castView(view, 2131427600, "field 'alipayBox'");
    view = finder.findRequiredView(source, 2131427598, "field 'alipayLL'");
    target.alipayLL = finder.castView(view, 2131427598, "field 'alipayLL'");
    view = finder.findRequiredView(source, 2131427602, "field 'wxPayBox'");
    target.wxPayBox = finder.castView(view, 2131427602, "field 'wxPayBox'");
    view = finder.findRequiredView(source, 2131427601, "field 'wxPaylipayLL'");
    target.wxPaylipayLL = finder.castView(view, 2131427601, "field 'wxPaylipayLL'");
    view = finder.findRequiredView(source, 2131427604, "field 'bankBox'");
    target.bankBox = finder.castView(view, 2131427604, "field 'bankBox'");
    view = finder.findRequiredView(source, 2131427603, "field 'bankLL'");
    target.bankLL = finder.castView(view, 2131427603, "field 'bankLL'");
    view = finder.findRequiredView(source, 2131427413, "field 'rechargeQuery'");
    target.rechargeQuery = finder.castView(view, 2131427413, "field 'rechargeQuery'");
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
