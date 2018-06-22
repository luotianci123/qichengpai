// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DynamicAdapter$$ViewBinder<T extends com.qcp.dfv.adapter.DynamicAdapter> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689737, "field 'userLy'");
    target.userLy = finder.castView(view, 2131689737, "field 'userLy'");
  }

  @Override public void unbind(T target) {
    target.userLy = null;
  }
}
