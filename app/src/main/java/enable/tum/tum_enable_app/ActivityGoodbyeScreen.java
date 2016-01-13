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
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivityGoodbyeScreen extends AppCompatActivity {

    private static final String TAG = "ActivityGoodByeScreen";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "in onCreateView");

        setContentView(R.layout.layout_good_bye);

        ImageButton restart = (ImageButton) findViewById(R.id.McD);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityGoodbyeScreen.this, ActivityStartScreen.class);
                startActivity(i);
            }
        });
    }
}