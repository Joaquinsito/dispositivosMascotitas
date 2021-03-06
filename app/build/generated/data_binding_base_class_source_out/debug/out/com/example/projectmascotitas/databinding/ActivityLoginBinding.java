// Generated by view binder compiler. Do not edit!
package com.example.projectmascotitas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.projectmascotitas.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button Login;

  @NonNull
  public final Button Register;

  @NonNull
  public final EditText emailLogin;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final EditText loginpassword;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button Login,
      @NonNull Button Register, @NonNull EditText emailLogin, @NonNull ImageView imageView2,
      @NonNull EditText loginpassword) {
    this.rootView = rootView;
    this.Login = Login;
    this.Register = Register;
    this.emailLogin = emailLogin;
    this.imageView2 = imageView2;
    this.loginpassword = loginpassword;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Login;
      Button Login = ViewBindings.findChildViewById(rootView, id);
      if (Login == null) {
        break missingId;
      }

      id = R.id.Register;
      Button Register = ViewBindings.findChildViewById(rootView, id);
      if (Register == null) {
        break missingId;
      }

      id = R.id.emailLogin;
      EditText emailLogin = ViewBindings.findChildViewById(rootView, id);
      if (emailLogin == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.loginpassword;
      EditText loginpassword = ViewBindings.findChildViewById(rootView, id);
      if (loginpassword == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, Login, Register, emailLogin,
          imageView2, loginpassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
