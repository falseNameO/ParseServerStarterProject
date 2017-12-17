package com.parse.starter.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.starter.InternalActivities.Add.AddActivity;
import com.parse.starter.InternalActivities.Likes.LikesActivity;
import com.parse.starter.InternalActivities.Profile.ProfileActivity;
import com.parse.starter.InternalActivities.Search.SerachActivity;
import com.parse.starter.R;
import com.parse.starter.InternalActivities.Home.UserFeed;

/**
 * Created by giste on 12/12/2017.
 */

public class BottomNavigationViewHelper {

    public static void setUpBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
       view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.ic_home:{
                            Intent intent1 = new Intent(context, UserFeed.class);
                            context.startActivity(intent1);
                            break;
                        }
                        case R.id.ic_search:{
                            Intent intent2 = new Intent(context, SerachActivity.class);
                            context.startActivity(intent2);
                            break;
                        }
                        case R.id.ic_add:{
                            Intent intent3 = new Intent(context, AddActivity.class);
                            context.startActivity(intent3);
                            break;
                        }
                        case R.id.ic_favorite:{
                            Intent intent4 = new Intent(context, LikesActivity.class);
                            context.startActivity(intent4);
                            break;
                        }
                        case R.id.ic_mypage:{
                            Intent intent5 = new Intent(context, ProfileActivity.class);
                            context.startActivity(intent5);
                            break;
                        }
                    }

               return false;
           }
       });
    }

}
