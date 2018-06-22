// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AdWebActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.AdWebActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689537, "field 'left'");
    target.left = finder.castView(view, 2131689537, "field 'left'");
    view = finder.findRequiredView(source, 2131689570, "field 'title'");
    target.title = finder.castView(view, 2131689570, "field 'title'");
    view = finder.findRequiredView(source, 2131689611, "field 'webView'");
    target.webView = finder.castView(view, 2131689611, "field 'webView'");
  }

  @Override public void unbind(T target) {
    target.left = null;
    target.title = null;
    target.webView = null;
  }
}
