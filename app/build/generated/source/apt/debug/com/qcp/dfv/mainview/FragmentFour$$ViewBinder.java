// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.mainview;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FragmentFour$$ViewBinder<T extends com.qcp.dfv.mainview.FragmentFour> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689754, "field 'oneTv'");
    target.oneTv = finder.castView(view, 2131689754, "field 'oneTv'");
    view = finder.findRequiredView(source, 2131689659, "field 'lineA'");
    target.lineA = view;
    view = finder.findRequiredView(source, 2131689753, "field 'oneLy' and method 'onViewClicked'");
    target.oneLy = finder.castView(view, 2131689753, "field 'oneLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689756, "field 'twoTv'");
    target.twoTv = finder.castView(view, 2131689756, "field 'twoTv'");
    view = finder.findRequiredView(source, 2131689662, "field 'lineB'");
    target.lineB = view;
    view = finder.findRequiredView(source, 2131689755, "field 'twoLy' and method 'onViewClicked'");
    target.twoLy = finder.castView(view, 2131689755, "field 'twoLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689752, "field 'headLy'");
    target.headLy = finder.castView(view, 2131689752, "field 'headLy'");
  }

  @Override public void unbind(T target) {
    target.oneTv = null;
    target.lineA = null;
    target.oneLy = null;
    target.twoTv = null;
    target.lineB = null;
    target.twoLy = null;
    target.headLy = null;
  }
}
