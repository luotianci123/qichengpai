// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HotActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.HotActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689537, "field 'left'");
    target.left = finder.castView(view, 2131689537, "field 'left'");
    view = finder.findRequiredView(source, 2131689613, "field 'ryView'");
    target.ryView = finder.castView(view, 2131689613, "field 'ryView'");
    view = finder.findRequiredView(source, 2131689612, "field 'swipely'");
    target.swipely = finder.castView(view, 2131689612, "field 'swipely'");
  }

  @Override public void unbind(T target) {
    target.left = null;
    target.ryView = null;
    target.swipely = null;
  }
}
