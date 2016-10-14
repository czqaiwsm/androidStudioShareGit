// Generated code from Butter Knife. Do not modify!
package com.share.learn.fragment.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ScheduleSettingFragment$$ViewBinder<T extends com.share.learn.fragment.home.ScheduleSettingFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427555, "field 'studentName'");
    target.studentName = finder.castView(view, 2131427555, "field 'studentName'");
    view = finder.findRequiredView(source, 2131427554, "field 'studentNameRl'");
    target.studentNameRl = finder.castView(view, 2131427554, "field 'studentNameRl'");
    view = finder.findRequiredView(source, 2131427557, "field 'subjectName'");
    target.subjectName = finder.castView(view, 2131427557, "field 'subjectName'");
    view = finder.findRequiredView(source, 2131427556, "field 'subjectRL'");
    target.subjectRL = finder.castView(view, 2131427556, "field 'subjectRL'");
    view = finder.findRequiredView(source, 2131427559, "field 'joniorName'");
    target.joniorName = finder.castView(view, 2131427559, "field 'joniorName'");
    view = finder.findRequiredView(source, 2131427558, "field 'joniorRl'");
    target.joniorRl = finder.castView(view, 2131427558, "field 'joniorRl'");
    view = finder.findRequiredView(source, 2131427561, "field 'frequencyName'");
    target.frequencyName = finder.castView(view, 2131427561, "field 'frequencyName'");
    view = finder.findRequiredView(source, 2131427560, "field 'frequencyRl'");
    target.frequencyRl = finder.castView(view, 2131427560, "field 'frequencyRl'");
    view = finder.findRequiredView(source, 2131427377, "field 'time'");
    target.time = finder.castView(view, 2131427377, "field 'time'");
    view = finder.findRequiredView(source, 2131427562, "field 'timeRl'");
    target.timeRl = finder.castView(view, 2131427562, "field 'timeRl'");
    view = finder.findRequiredView(source, 2131427563, "field 'sure'");
    target.sure = finder.castView(view, 2131427563, "field 'sure'");
  }

  @Override public void unbind(T target) {
    target.studentName = null;
    target.studentNameRl = null;
    target.subjectName = null;
    target.subjectRL = null;
    target.joniorName = null;
    target.joniorRl = null;
    target.frequencyName = null;
    target.frequencyRl = null;
    target.time = null;
    target.timeRl = null;
    target.sure = null;
  }
}
