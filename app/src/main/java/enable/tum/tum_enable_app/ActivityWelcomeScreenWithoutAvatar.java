package enable.tum.tum_enable_app;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Felix Naser on 14.12.2015.
 */
public class ActivityWelcomeScreenWithoutAvatar extends AppCompatActivity {

    private static final String TAG = "ActWelScWithoutAvatar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_welcome_screen_without_avatar);

        TestVersions testVersion = (TestVersions) getIntent().getSerializableExtra(getResources().getString(R.string.strTestVersion));

        final EditText mEingabe = (EditText) findViewById(R.id.txtKcal);

        Button bestellungStarten = (Button) findViewById(R.id.bestellungStarten);
        bestellungStarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent current = getIntent();
                TestVersions version = (TestVersions) current.getSerializableExtra(getResources().getString(R.string.strTestVersion));

                Intent newIntent = new Intent(ActivityWelcomeScreenWithoutAvatar.this, ActivityOrderingScreen.class);
                newIntent.putExtra(getResources().getString(R.string.strTestVersion), version);
                startActivity(newIntent);

                ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
                instance.updateplanedkcalIntake(Double.valueOf(mEingabe.getText().toString()));

                Log.d(TAG, mEingabe.getText().toString());
            }
        });

        final ImageButton infoKcal = (ImageButton) findViewById(R.id.infobtn_without_avatar);
        infoKcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment infoKcal = new InfoKcal();
                infoKcal.show(getFragmentManager(), "kcal");
                Log.d(TAG, "infoKcal");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        instance.resetProgrammLogicSingleton();
    }
}
