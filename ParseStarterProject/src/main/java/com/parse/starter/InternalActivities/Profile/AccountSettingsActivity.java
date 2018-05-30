package com.parse.starter.InternalActivities.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.starter.R;

import java.util.ArrayList;

/**
 * Created by giste on 3/20/2018.
 */

public class AccountSettingsActivity extends AppCompatActivity {

    public static final String TAG = "AccountSettingsActivity";
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);

        setupSettingsList();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList");
        ListView listView = (ListView) findViewById(R.id.lvAccountOptions);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.photos_of_you));
        options.add(getString(R.string.saved));
        options.add(getString(R.string.story_settings));
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.change_password));
        options.add(getString(R.string.posts_you_liked));
        options.add(getString(R.string.blocked_users));
        options.add(getString(R.string.private_account));

        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);

        listView.setAdapter(arrayAdapter);
    }
}
