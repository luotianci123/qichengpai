// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.mainview;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FragmentTwo$$ViewBinder<T extends com.qcp.dfv.mainview.FragmentTwo> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689570, "field 'title'");
    target.title = finder.castView(view, 2131689570, "field 'title'");
    view = finder.findRequiredView(source, 2131689759, "method 'onViewClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.title = null;
  }
}
