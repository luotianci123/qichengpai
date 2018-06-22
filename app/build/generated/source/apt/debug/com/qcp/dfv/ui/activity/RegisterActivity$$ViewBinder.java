// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689629, "field 'phone'");
    target.phone = finder.castView(view, 2131689629, "field 'phone'");
    view = finder.findRequiredView(source, 2131689630, "field 'password'");
    target.password = finder.castView(view, 2131689630, "field 'password'");
    view = finder.findRequiredView(source, 2131689673, "field 'code'");
    target.code = finder.castView(view, 2131689673, "field 'code'");
    view = finder.findRequiredView(source, 2131689674, "field 'getCode' and method 'onViewClicked'");
    target.getCode = finder.castView(view, 2131689674, "field 'getCode'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689675, "field 'registerBt' and method 'onViewClicked'");
    target.registerBt = finder.castView(view, 2131689675, "field 'registerBt'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.phone = null;
    target.password = null;
    target.code = null;
    target.getCode = null;
    target.registerBt = null;
  }
}
