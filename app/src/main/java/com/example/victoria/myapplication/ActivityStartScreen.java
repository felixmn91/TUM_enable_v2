package com.example.victoria.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class ActivityStartScreen extends AppCompatActivity {

    // Define Tag for log messanges

    private static final String TAG = "ActivityStartScreen";

    // Define Data between Activities

    // private static final String SELECTED_VERSION = "com.example.android.selected_version";

    //Defining Buttons

    private Button mNoAvatarNoNudging;
    private Button mNoAvatarNudging;
    private Button mAvatarNoNudging;
    private Button mAvatarNudging;

    private String mSelectedVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start_screen);

        //Instantiate Buttons and set Listeners

        mNoAvatarNoNudging = (Button) findViewById(R.id.no_avatar_no_nudging);
        mNoAvatarNoNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to Standard Teminal version
                mSelectedVersion = "StandardVersion";
                mLaunchTerminal(mSelectedVersion);
            }
        });

        mNoAvatarNudging = (Button) findViewById(R.id.no_avatar_nudging);
        mNoAvatarNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to only Nudging
                mSelectedVersion = "NudgingOnly";
                mLaunchTerminal(mSelectedVersion);
            }
        });

        mAvatarNoNudging = (Button) findViewById(R.id.avatar_no_nudging);
        mAvatarNoNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to only Avatar
                mSelectedVersion = "AvatarOnly";
                mLaunchTerminal(mSelectedVersion);
            }
        });

        mAvatarNudging = (Button) findViewById(R.id.avatar_nudging);
        mAvatarNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use Combined Version
                mSelectedVersion = "CombinedVersion";
                mLaunchTerminal(mSelectedVersion);
            }
        });
    }

    public void mLaunchTerminal(String mVersion) {
        Intent i = new Intent(ActivityStartScreen.this, ActivityOrderingScreen.class);
        i.putExtra("SELECTED_VERSION", mVersion);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
