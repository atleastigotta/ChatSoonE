// Generated by view binder compiler. Do not edit!
package com.chat_soon_e.chat_soon_e.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import com.chat_soon_e.chat_soon_e.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityMainDrawerSwitchBinding implements ViewBinding {
  @NonNull
  private final SwitchCompat rootView;

  private ActivityMainDrawerSwitchBinding(@NonNull SwitchCompat rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public SwitchCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainDrawerSwitchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainDrawerSwitchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main_drawer_switch, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainDrawerSwitchBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new ActivityMainDrawerSwitchBinding((SwitchCompat) rootView);
  }
}