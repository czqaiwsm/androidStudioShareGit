// Generated code from Butter Knife. Do not modify!
package com.share.learn.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatMsgViewAdapter$ViewHolder$$ViewBinder<T extends com.share.learn.adapter.ChatMsgViewAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427357, "field 'comtime'");
    target.comtime = finder.castView(view, 2131427357, "field 'comtime'");
    view = finder.findRequiredView(source, 2131427360, "field 'comhead'");
    target.comhead = finder.castView(view, 2131427360, "field 'comhead'");
    view = finder.findRequiredView(source, 2131427361, "field 'comChatcontent'");
    target.comChatcontent = finder.castView(view, 2131427361, "field 'comChatcontent'");
    view = finder.findRequiredView(source, 2131427362, "field 'comUsername'");
    target.comUsername = finder.castView(view, 2131427362, "field 'comUsername'");
    view = finder.findRequiredView(source, 2131427356, "field 'comMsgLL'");
    target.comMsgLL = finder.castView(view, 2131427356, "field 'comMsgLL'");
    view = finder.findRequiredView(source, 2131427364, "field 'sendtime'");
    target.sendtime = finder.castView(view, 2131427364, "field 'sendtime'");
    view = finder.findRequiredView(source, 2131427366, "field 'sendUserHead'");
    target.sendUserHead = finder.castView(view, 2131427366, "field 'sendUserHead'");
    view = finder.findRequiredView(source, 2131427367, "field 'sendcontent'");
    target.sendcontent = finder.castView(view, 2131427367, "field 'sendcontent'");
    view = finder.findRequiredView(source, 2131427368, "field 'send_username'");
    target.send_username = finder.castView(view, 2131427368, "field 'send_username'");
    view = finder.findRequiredView(source, 2131427363, "field 'sendMsgLL'");
    target.sendMsgLL = finder.castView(view, 2131427363, "field 'sendMsgLL'");
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
