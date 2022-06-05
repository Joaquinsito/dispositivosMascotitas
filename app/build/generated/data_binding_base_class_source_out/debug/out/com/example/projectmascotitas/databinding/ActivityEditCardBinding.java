// Generated by view binder compiler. Do not edit!
package com.example.projectmascotitas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projectmascotitas.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditCardBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText cardnumber;

  @NonNull
  public final EditText ccv;

  @NonNull
  public final TextView cnum;

  @NonNull
  public final ListView datospago;

  @NonNull
  public final Button deletepay;

  @NonNull
  public final EditText duedate;

  @NonNull
  public final Button savepay;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final Button updatepay;

  private ActivityEditCardBinding(@NonNull ConstraintLayout rootView, @NonNull EditText cardnumber,
      @NonNull EditText ccv, @NonNull TextView cnum, @NonNull ListView datospago,
      @NonNull Button deletepay, @NonNull EditText duedate, @NonNull Button savepay,
      @NonNull TextView textView13, @NonNull TextView textView14, @NonNull Button updatepay) {
    this.rootView = rootView;
    this.cardnumber = cardnumber;
    this.ccv = ccv;
    this.cnum = cnum;
    this.datospago = datospago;
    this.deletepay = deletepay;
    this.duedate = duedate;
    this.savepay = savepay;
    this.textView13 = textView13;
    this.textView14 = textView14;
    this.updatepay = updatepay;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardnumber;
      EditText cardnumber = ViewBindings.findChildViewById(rootView, id);
      if (cardnumber == null) {
        break missingId;
      }

      id = R.id.ccv;
      EditText ccv = ViewBindings.findChildViewById(rootView, id);
      if (ccv == null) {
        break missingId;
      }

      id = R.id.cnum;
      TextView cnum = ViewBindings.findChildViewById(rootView, id);
      if (cnum == null) {
        break missingId;
      }

      id = R.id.datospago;
      ListView datospago = ViewBindings.findChildViewById(rootView, id);
      if (datospago == null) {
        break missingId;
      }

      id = R.id.deletepay;
      Button deletepay = ViewBindings.findChildViewById(rootView, id);
      if (deletepay == null) {
        break missingId;
      }

      id = R.id.duedate;
      EditText duedate = ViewBindings.findChildViewById(rootView, id);
      if (duedate == null) {
        break missingId;
      }

      id = R.id.savepay;
      Button savepay = ViewBindings.findChildViewById(rootView, id);
      if (savepay == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.updatepay;
      Button updatepay = ViewBindings.findChildViewById(rootView, id);
      if (updatepay == null) {
        break missingId;
      }

      return new ActivityEditCardBinding((ConstraintLayout) rootView, cardnumber, ccv, cnum,
          datospago, deletepay, duedate, savepay, textView13, textView14, updatepay);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
