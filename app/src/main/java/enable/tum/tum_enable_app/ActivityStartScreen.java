package enable.tum.tum_enable_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

public class ActivityStartScreen extends AppCompatActivity {

    private static final String TAG = "ActivityStartScreen";

    private Button mNoAvatarNoNudging;
    private Button mNoAvatarNudging;
    private Button mAvatarNoNudging;
    private Button mAvatarNudging;

    private TestVersion mSelectedVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.layout_start_screen);
        //Instantiate Buttons and set Listeners

        mNoAvatarNoNudging = (Button) findViewById(R.id.no_avatar_no_nudging);
        mNoAvatarNoNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedVersion = TestVersion.avatar_off_nudging_off;
                mLaunchTerminal(mSelectedVersion, "NoAvatar");
            }
        });

        mNoAvatarNudging = (Button) findViewById(R.id.no_avatar_nudging);
        mNoAvatarNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedVersion = TestVersion.avatar_off_nudging_on;
                Log.d(TAG, mSelectedVersion.toString());
                mLaunchTerminal(mSelectedVersion, "NoAvatar");
            }
        });

        mAvatarNoNudging = (Button) findViewById(R.id.avatar_no_nudging);
        mAvatarNoNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedVersion = TestVersion.avatar_on_nudging_off;
                mLaunchTerminal(mSelectedVersion, "Avatar");
            }
        });

        mAvatarNudging = (Button) findViewById(R.id.avatar_nudging);
        mAvatarNudging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedVersion = TestVersion.avatar_on_nudging_on;
                mLaunchTerminal(mSelectedVersion, "Avatar");
            }
        });
    }

    public void mLaunchTerminal(TestVersion version, String s) {
        if (s.equals("Avatar")) {
            Intent i = new Intent(ActivityStartScreen.this, ActivityWelcomeScreen.class);
            i.putExtra(getResources().getString(R.string.strTestVersion), version);
            startActivity(i);
        } else {
            Intent i = new Intent(ActivityStartScreen.this, ActivityWelcomeScreenWithoutAvatar.class);
            i.putExtra(getResources().getString(R.string.strTestVersion), version);
            startActivity(i);
        }
    }
}
