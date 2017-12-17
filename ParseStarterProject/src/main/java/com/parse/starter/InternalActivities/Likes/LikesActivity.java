package com.parse.starter.InternalActivities.Likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.starter.InternalActivities.Home.UserFeed;
import com.parse.starter.R;
import com.parse.starter.Utils.BottomNavigationViewHelper;

/**
 * Created by giste on 12/12/2017.
 */

public class LikesActivity extends AppCompatActivity {
    public static final String TAG = "LikesActivity";

    private static final int ACTIVITY_NUM = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Log.d(TAG, "onCreate Started");
        UserFeed.LogoSet.setVisibility(View.INVISIBLE);
        setUpBottomNavigationView();
    }

    public void setUpBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(LikesActivity.this, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
