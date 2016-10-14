// Generated code from Butter Knife. Do not modify!
package com.share.teacher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatMsgViewAdapter$ViewHolder$$ViewBinder<T extends com.share.teacher.adapter.ChatMsgViewAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492896, "field 'comtime'");
    target.comtime = finder.castView(view, 2131492896, "field 'comtime'");
    view = finder.findRequiredView(source, 2131492899, "field 'comhead'");
    target.comhead = finder.castView(view, 2131492899, "field 'comhead'");
    view = finder.findRequiredView(source, 2131492900, "field 'comChatcontent'");
    target.comChatcontent = finder.castView(view, 2131492900, "field 'comChatcontent'");
    view = finder.findRequiredView(source, 2131492901, "field 'comUsername'");
    target.comUsername = finder.castView(view, 2131492901, "field 'comUsername'");
    view = finder.findRequiredView(source, 2131492895, "field 'comMsgLL'");
    target.comMsgLL = finder.castView(view, 2131492895, "field 'comMsgLL'");
    view = finder.findRequiredView(source, 2131492903, "field 'sendtime'");
    target.sendtime = finder.castView(view, 2131492903, "field 'sendtime'");
    view = finder.findRequiredView(source, 2131492905, "field 'sendUserHead'");
    target.sendUserHead = finder.castView(view, 2131492905, "field 'sendUserHead'");
    view = finder.findRequiredView(source, 2131492906, "field 'sendcontent'");
    target.sendcontent = finder.castView(view, 2131492906, "field 'sendcontent'");
    view = finder.findRequiredView(source, 2131492907, "field 'send_username'");
    target.send_username = finder.castView(view, 2131492907, "field 'send_username'");
    view = finder.findRequiredView(source, 2131492902, "field 'sendMsgLL'");
    target.sendMsgLL = finder.castView(view, 2131492902, "field 'sendMsgLL'");
  }

  @Override public void unbind(T target) {
    target.comtime = null;
    target.comhead = null;
    target.comChatcontent = null;
    target.comUsername = null;
    target.comMsgLL = null;
    target.sendtime = null;
    target.sendUserHead = null;
    target.sendcontent = null;
    target.send_username = null;
    target.sendMsgLL = null;
  }
}
