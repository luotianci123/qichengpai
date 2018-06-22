// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WeizhangCheckActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.WeizhangCheckActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689537, "field 'left'");
    target.left = finder.castView(view, 2131689537, "field 'left'");
  }

  @Override public void unbind(T target) {
    target.left = null;
  }
}
