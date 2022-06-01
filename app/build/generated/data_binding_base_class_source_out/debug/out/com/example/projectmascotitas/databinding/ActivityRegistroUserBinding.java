// Generated by view binder compiler. Do not edit!
package com.example.projectmascotitas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projectmascotitas.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegistroUserBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText addressbtn;

  @NonNull
  public final Button buttonRegisters;

  @NonNull
  public final EditText confirmPassword;

  @NonNull
  public final EditText lastnameR;

  @NonNull
  public final EditText mailRegister;

  @NonNull
  public final EditText nameR;

  @NonNull
  public final EditText password;

  @NonNull
  public final EditText phoneRegister;

  private ActivityRegistroUserBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText addressbtn, @NonNull Button buttonRegisters,
      @NonNull EditText confirmPassword, @NonNull EditText lastnameR,
      @NonNull EditText mailRegister, @NonNull EditText nameR, @NonNull EditText password,
      @NonNull EditText phoneRegister) {
    this.rootView = rootView;
    this.addressbtn = addressbtn;
    this.buttonRegisters = buttonRegisters;
    this.confirmPassword = confirmPassword;
    this.lastnameR = lastnameR;
    this.mailRegister = mailRegister;
    this.nameR = nameR;
    this.password = password;
    this.phoneRegister = phoneRegister;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegistroUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegistroUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_registro_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegistroUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addressbtn;
      EditText addressbtn = ViewBindings.findChildViewById(rootView, id);
      if (addressbtn == null) {
        break missingId;
      }

      id = R.id.buttonRegisters;
      Button buttonRegisters = ViewBindings.findChildViewById(rootView, id);
      if (buttonRegisters == null) {
        break missingId;
      }

      id = R.id.confirmPassword;
      EditText confirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (confirmPassword == null) {
        break missingId;
      }

      id = R.id.lastnameR;
      EditText lastnameR = ViewBindings.findChildViewById(rootView, id);
      if (lastnameR == null) {
        break missingId;
      }

      id = R.id.mailRegister;
      EditText mailRegister = ViewBindings.findChildViewById(rootView, id);
      if (mailRegister == null) {
        break missingId;
      }

      id = R.id.nameR;
      EditText nameR = ViewBindings.findChildViewById(rootView, id);
      if (nameR == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.phoneRegister;
      EditText phoneRegister = ViewBindings.findChildViewById(rootView, id);
      if (phoneRegister == null) {
        break missingId;
      }

      return new ActivityRegistroUserBinding((ConstraintLayout) rootView, addressbtn,
          buttonRegisters, confirmPassword, lastnameR, mailRegister, nameR, password,
          phoneRegister);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
