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

public class ActivityGoodbyeScreen extends AppCompatActivity {

    private static final String TAG = "ActivityGoodByeScreen";

    private TestVersion mSelectedVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "in onCreateView");

        setContentView(R.layout.layout_good_bye);

    }
}