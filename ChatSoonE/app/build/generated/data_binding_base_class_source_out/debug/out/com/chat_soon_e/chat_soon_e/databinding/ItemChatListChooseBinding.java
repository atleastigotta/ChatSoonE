// Generated by view binder compiler. Do not edit!
package com.chat_soon_e.chat_soon_e.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat_soon_e.chat_soon_e.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemChatListChooseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout itemChatListChooseLayout;

  @NonNull
  public final TextView itemChatListContentTv;

  @NonNull
  public final TextView itemChatListDateTimeTv;

  @NonNull
  public final TextView itemChatListNameTv;

  @NonNull
  public final CardView itemChatListProfileCv;

  @NonNull
  public final ImageView itemChatListProfileIv;

  private ItemChatListChooseBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout itemChatListChooseLayout, @NonNull TextView itemChatListContentTv,
      @NonNull TextView itemChatListDateTimeTv, @NonNull TextView itemChatListNameTv,
      @NonNull CardView itemChatListProfileCv, @NonNull ImageView itemChatListProfileIv) {
    this.rootView = rootView;
    this.itemChatListChooseLayout = itemChatListChooseLayout;
    this.itemChatListContentTv = itemChatListContentTv;
    this.itemChatListDateTimeTv = itemChatListDateTimeTv;
    this.itemChatListNameTv = itemChatListNameTv;
    this.itemChatListProfileCv = itemChatListProfileCv;
    this.itemChatListProfileIv = itemChatListProfileIv;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemChatListChooseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemChatListChooseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_chat_list_choose, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemChatListChooseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout itemChatListChooseLayout = (ConstraintLayout) rootView;

      id = R.id.item_chat_list_content_tv;
      TextView itemChatListContentTv = ViewBindings.findChildViewById(rootView, id);
      if (itemChatListContentTv == null) {
        break missingId;
      }

      id = R.id.item_chat_list_date_time_tv;
      TextView itemChatListDateTimeTv = ViewBindings.findChildViewById(rootView, id);
      if (itemChatListDateTimeTv == null) {
        break missingId;
      }

      id = R.id.item_chat_list_name_tv;
      TextView itemChatListNameTv = ViewBindings.findChildViewById(rootView, id);
      if (itemChatListNameTv == null) {
        break missingId;
      }

      id = R.id.item_chat_list_profile_cv;
      CardView itemChatListProfileCv = ViewBindings.findChildViewById(rootView, id);
      if (itemChatListProfileCv == null) {
        break missingId;
      }

      id = R.id.item_chat_list_profile_iv;
      ImageView itemChatListProfileIv = ViewBindings.findChildViewById(rootView, id);
      if (itemChatListProfileIv == null) {
        break missingId;
      }

      return new ItemChatListChooseBinding((ConstraintLayout) rootView, itemChatListChooseLayout,
          itemChatListContentTv, itemChatListDateTimeTv, itemChatListNameTv, itemChatListProfileCv,
          itemChatListProfileIv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
