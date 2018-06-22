// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689629, "field 'phoneEt'");
    target.phoneEt = finder.castView(view, 2131689629, "field 'phoneEt'");
    view = finder.findRequiredView(source, 2131689630, "field 'passwordEt'");
    target.passwordEt = finder.castView(view, 2131689630, "field 'passwordEt'");
    view = finder.findRequiredView(source, 2131689631, "field 'boxPwd' and method 'onViewClicked'");
    target.boxPwd = finder.castView(view, 2131689631, "field 'boxPwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689634, "field 'loginBt' and method 'onViewClicked'");
    target.loginBt = finder.castView(view, 2131689634, "field 'loginBt'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689632, "field 'forgotPwdTv' and method 'onViewClicked'");
    target.forgotPwdTv = finder.castView(view, 2131689632, "field 'forgotPwdTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689633, "field 'registerTv' and method 'onViewClicked'");
    target.registerTv = finder.castView(view, 2131689633, "field 'registerTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689635, "method 'onViewClicked'");
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
    target.phoneEt = null;
    target.passwordEt = null;
    target.boxPwd = null;
    target.loginBt = null;
    target.forgotPwdTv = null;
    target.registerTv = null;
  }
}
