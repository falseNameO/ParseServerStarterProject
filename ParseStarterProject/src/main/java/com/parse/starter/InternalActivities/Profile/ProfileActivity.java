package com.parse.starter.InternalActivities.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.ParseUser;
import com.parse.starter.InternalActivities.Home.UserFeed;
import com.parse.starter.R;
import com.parse.starter.Utils.BottomNavigationViewHelper;

/**
 * Created by giste on 12/12/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "ProfileActivity";

    private static final int ACTIVITY_NUM = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate Started");
        UserFeed.LogoSet.setVisibility(View.INVISIBLE);




        GridView gridViewProifle = (GridView) findViewById(R.id.gridViewProfile);
        TextView noPosts = (TextView) findViewById(R.id.noPosts);
        TextView website = (TextView) findViewById(R.id.website);
        TextView description = (TextView) findViewById(R.id.description);
        TextView fullname = (TextView) findViewById(R.id.display_name);
        TextView tvUserProfile = (TextView) findViewById(R.id.usernameProfile);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String parseUser = currentUser.getUsername();
        tvUserProfile.setText(parseUser);

        parseUser = setParseUser(parseUser, currentUser, "fullname");
        setText(parseUser, fullname);

        parseUser = setParseUser(parseUser, currentUser, "description");
        setText(parseUser, description);

        parseUser = setParseUser(parseUser, currentUser, "website");
        setText(parseUser, website);

        if (gridViewProifle.getCount() == 0){
            noPosts.setVisibility(TextView.VISIBLE);
        }

        setUpBottomNavigationView();
        setupToolBar();

    }

    private String setParseUser(String parseUser,  ParseUser currentUser, String getColumnString){
        parseUser = "";
        if (currentUser.get(getColumnString) != null){
            parseUser = currentUser.get(getColumnString).toString();
        }

        return parseUser;
    }

    private void setText(String parseUser, TextView tv){

        if(parseUser == ""){
            tv.setHeight(1);

        }else
            tv.setText(parseUser);

    }

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView menu = (ImageView) findViewById(R.id.profileMenu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings");
                Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setUpBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationViewEx);


        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
