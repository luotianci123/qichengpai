// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PictureShareActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.PictureShareActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689671, "field 'GridView'");
    target.GridView = finder.castView(view, 2131689671, "field 'GridView'");
    view = finder.findRequiredView(source, 2131689670, "field 'contentEt'");
    target.contentEt = finder.castView(view, 2131689670, "field 'contentEt'");
    view = finder.findRequiredView(source, 2131689730, "method 'onViewClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689846, "method 'onViewClicked'");
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
    target.GridView = null;
    target.contentEt = null;
  }
}
