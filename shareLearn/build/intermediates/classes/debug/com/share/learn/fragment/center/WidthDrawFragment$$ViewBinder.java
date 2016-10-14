// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.center;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WidthDrawFragment$$ViewBinder<T extends com.share.learn.fragment.center.WidthDrawFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427595, "field 'alipayAccount'");
    target.alipayAccount = finder.castView(view, 2131427595, "field 'alipayAccount'");
    view = finder.findRequiredView(source, 2131427596, "field 'alipayName'");
    target.alipayName = finder.castView(view, 2131427596, "field 'alipayName'");
    view = finder.findRequiredView(source, 2131427413, "field 'rechargeQuery'");
    target.rechargeQuery = finder.castView(view, 2131427413, "field 'rechargeQuery'");
    view = finder.findRequiredView(source, 2131427597, "field 'drawMoney'");
    target.drawMoney = finder.castView(view, 2131427597, "field 'drawMoney'");
    view = finder.findRequiredView(source, 2131427527, "field 'register_passCode'");
    target.register_passCode = finder.castView(view, 2131427527, "field 'register_passCode'");
    view = finder.findRequiredView(source, 2131427528, "field 'register_getCode'");
    target.register_getCode = finder.castView(view, 2131427528, "field 'register_getCode'");
  }

  @Override public void unbind(T target) {
    target.alipayAccount = null;
    target.alipayName = null;
    target.rechargeQuery = null;
    target.drawMoney = null;
    target.register_passCode = null;
    target.register_getCode = null;
  }
}
