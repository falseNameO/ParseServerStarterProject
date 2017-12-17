package com.parse.starter.MainLoginSignUp.Login.SignUp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText emailEditText, nameEditText, usernaEditText, passEditText;
    Button signIn;

    LinearLayout linearLayout;

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    static public void EmptySection(EditText text){
        text.setError("This section is empty!");
    }

    static public void showError(TextView mEditText, Activity activity) {
        Animation shake = AnimationUtils.loadAnimation(activity, R.anim.shake);
        mEditText.startAnimation(shake);
    }

    static public  void setTintColor(int color, TextView textView){
        Drawable drawable = textView.getBackground();

        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        if (Build.VERSION.SDK_INT > 16) {
            textView.setBackground(drawable);

        } else {
            textView.setCompoundDrawables(null,null,drawable,null);

        }
    }

    static public void setTintColorBack(final TextView textView, final int colorblack){
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setTintColor(colorblack,textView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signIn = (Button) findViewById(R.id.signInButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        nameEditText = (EditText) findViewById(R.id.fullnameEditText);
        usernaEditText = (EditText) findViewById(R.id.usernameEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutSignUp);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {


                final String email, fullname, username, password;
                email = emailEditText.getText().toString();
                fullname = nameEditText.getText().toString();
                username = usernaEditText.getText().toString();
                password = passEditText.getText().toString();

                if (!(email.isEmpty() || fullname.isEmpty() || username.isEmpty() || password.isEmpty())){
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    currentUser.logOut();

                    ParseUser user = new ParseUser();

                    user.put("fullname", fullname);
                    user.setEmail(email);
                    user.setUsername(username);
                    user.setPassword(password);


                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            int color = Color.parseColor("#FF0000");
                           if (e == null){
                            Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                               finish();
                        }else {

                               switch (e.getCode()){
                                   case ParseException.EMAIL_TAKEN:{
                                       emailEditText.setText("");
                                       emailEditText.setError("This email is already taken.");
                                       setTintColor(color, emailEditText);
                                       showError(emailEditText, SignUpActivity.this);
                                       break;
                                   }
                                   case ParseException.INVALID_EMAIL_ADDRESS:{
                                       emailEditText.setText("");
                                       emailEditText.setError("The email address is invalid.");
                                       setTintColor(color, emailEditText);
                                       showError(emailEditText, SignUpActivity.this);
                                       break;
                                   }
                                   case  ParseException.USERNAME_TAKEN:{
                                       usernaEditText.setText("");
                                       usernaEditText.setError("This username is invalid.");
                                       setTintColor(color, usernaEditText);
                                       showError(usernaEditText, SignUpActivity.this);
                                       break;
                                   }
                                   default:


                                       if (password.toString().length() < 8 && !isValidPassword(password.toString())){
                                           passEditText.setText("");
                                           passEditText.setError("Password invalid.");
                                           setTintColor(color, passEditText);
                                           showError(passEditText, SignUpActivity.this);
                                       }

                                       Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                               }


                               Toast.makeText(SignUpActivity.this, "Sign up failed "+ e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                    });


                }else{
                        int color = Color.parseColor("#FF0000");

                    if (email.isEmpty()){
                        setTintColor(color, emailEditText);

                        showError(emailEditText, SignUpActivity.this);

                        EmptySection(emailEditText);
                    }
                    if (fullname.isEmpty()){
                        setTintColor(color, nameEditText);

                        showError(nameEditText, SignUpActivity.this);

                        EmptySection(nameEditText);
                    }
                    if (username.isEmpty()){
                        setTintColor(color, usernaEditText);

                        showError(usernaEditText, SignUpActivity.this);

                        EmptySection(usernaEditText);
                    }
                    if (password.isEmpty()){
                        setTintColor(color, passEditText);

                        showError(passEditText, SignUpActivity.this);

                        EmptySection(passEditText);

                    }
                }

            }



        });

        int colorBlack = Color.parseColor("#000000");
        setTintColorBack(emailEditText, colorBlack);
        setTintColorBack(nameEditText, colorBlack);
        setTintColorBack(usernaEditText, colorBlack);
        setTintColorBack(passEditText, colorBlack);
    }
}
