/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.MainLoginSignUp.Login.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;

import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.MainLoginSignUp.Login.SignUp.SignUpActivity;
import com.parse.starter.InternalActivities.Home.UserFeed;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

  TextView signIn;
  EditText usernameEditText, passEditText;
  Button logIn;
  LinearLayout linearLayout;

  private void onClickLogIn(){

    ParseUser currentUser = ParseUser.getCurrentUser();
    currentUser.logOut();
    ParseUser user = new ParseUser();
    String username = usernameEditText.getText().toString();
    String userPass = passEditText.getText().toString();
    if (!(username.isEmpty() || userPass.isEmpty())){

      user.logInInBackground(username, userPass, new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
          int color = Color.parseColor("#FF0000");
          if (e == null){
            Intent intent = new Intent(MainActivity.this, UserFeed.class);
            startActivity(intent);

          } else{

            usernameEditText.setError("Username or password incorrect");
            passEditText.setError("Username or password incorrect");

            passEditText.setText("");
            SignUpActivity.showError(usernameEditText, MainActivity.this);
            SignUpActivity.showError(passEditText, MainActivity.this);
            SignUpActivity.setTintColor(color, usernameEditText);
            SignUpActivity.setTintColor(color, passEditText);

          }
        }
      });

    }else {

      int color = Color.parseColor("#FF0000");
      if (username.isEmpty()){
        SignUpActivity.EmptySection(usernameEditText);

        SignUpActivity.setTintColor(color, usernameEditText);

        SignUpActivity.showError(usernameEditText, MainActivity.this);
      }
      if (userPass.isEmpty()){
        SignUpActivity.EmptySection(passEditText);

        SignUpActivity.setTintColor(color, passEditText);

        SignUpActivity.showError(passEditText, MainActivity.this);
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
    logIn = (Button) findViewById(R.id.logInButton);
    usernameEditText = (EditText) findViewById(R.id.logInEditText);
    passEditText = (EditText) findViewById(R.id.passEditText);
    linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLogIn);

    passEditText.setOnKeyListener(this);
    linearLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
      }
    });
    signIn = (TextView) findViewById(R.id.signIn);

    signIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
         startActivity(intent);
      }
    });

    logIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onClickLogIn();
      }
    });

    int colorBlack = Color.parseColor("#000000");
    SignUpActivity.setTintColorBack(usernameEditText, colorBlack);
    SignUpActivity.setTintColorBack(passEditText, colorBlack);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public boolean onKey(View view, int i, KeyEvent keyEvent) {

    if (i == keyEvent.KEYCODE_ENTER){
      onClickLogIn();
    }

    return false;
  }
}
