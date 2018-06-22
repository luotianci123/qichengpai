// Generated code from Butter Knife. Do not modify!
package com.qcp.dfv.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyInfoActivity$$ViewBinder<T extends com.qcp.dfv.ui.activity.MyInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689618, "field 'nameTv'");
    target.nameTv = finder.castView(view, 2131689618, "field 'nameTv'");
    view = finder.findRequiredView(source, 2131689655, "field 'signatureTv'");
    target.signatureTv = finder.castView(view, 2131689655, "field 'signatureTv'");
    view = finder.findRequiredView(source, 2131689656, "field 'genderTv'");
    target.genderTv = finder.castView(view, 2131689656, "field 'genderTv'");
    view = finder.findRequiredView(source, 2131689619, "field 'addressTv'");
    target.addressTv = finder.castView(view, 2131689619, "field 'addressTv'");
    view = finder.findRequiredView(source, 2131689658, "field 'fansTv'");
    target.fansTv = finder.castView(view, 2131689658, "field 'fansTv'");
    view = finder.findRequiredView(source, 2131689659, "field 'lineA'");
    target.lineA = view;
    view = finder.findRequiredView(source, 2131689657, "field 'fansLy' and method 'onViewClicked'");
    target.fansLy = finder.castView(view, 2131689657, "field 'fansLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689661, "field 'followingTv'");
    target.followingTv = finder.castView(view, 2131689661, "field 'followingTv'");
    view = finder.findRequiredView(source, 2131689662, "field 'lineB'");
    target.lineB = view;
    view = finder.findRequiredView(source, 2131689660, "field 'followingLy' and method 'onViewClicked'");
    target.followingLy = finder.castView(view, 2131689660, "field 'followingLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689664, "field 'dynamicTv'");
    target.dynamicTv = finder.castView(view, 2131689664, "field 'dynamicTv'");
    view = finder.findRequiredView(source, 2131689665, "field 'lineC'");
    target.lineC = view;
    view = finder.findRequiredView(source, 2131689663, "field 'dynamicLy' and method 'onViewClicked'");
    target.dynamicLy = finder.castView(view, 2131689663, "field 'dynamicLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689667, "field 'collectionTv'");
    target.collectionTv = finder.castView(view, 2131689667, "field 'collectionTv'");
    view = finder.findRequiredView(source, 2131689668, "field 'lineD'");
    target.lineD = view;
    view = finder.findRequiredView(source, 2131689666, "field 'collectionLy' and method 'onViewClicked'");
    target.collectionLy = finder.castView(view, 2131689666, "field 'collectionLy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689669, "field 'myContentFly'");
    target.myContentFly = finder.castView(view, 2131689669, "field 'myContentFly'");
    view = finder.findRequiredView(source, 2131689654, "method 'onViewClicked'");
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
    target.nameTv = null;
    target.signatureTv = null;
    target.genderTv = null;
    target.addressTv = null;
    target.fansTv = null;
    target.lineA = null;
    target.fansLy = null;
    target.followingTv = null;
    target.lineB = null;
    target.followingLy = null;
    target.dynamicTv = null;
    target.lineC = null;
    target.dynamicLy = null;
    target.collectionTv = null;
    target.lineD = null;
    target.collectionLy = null;
    target.myContentFly = null;
  }
}
