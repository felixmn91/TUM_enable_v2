package enable.tum.tum_enable_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Felix Naser on 14.12.2015.
 */
public class ActivityWelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_welcome_screen);

        Button bestellungStarten = (Button) findViewById(R.id.bestellungStarten);
        bestellungStarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent current = getIntent();
                TestVersion version = (TestVersion) current.getSerializableExtra(getResources().getString(R.string.strTestVersion));

                Intent newIntent = new Intent(ActivityWelcomeScreen.this, ActivityOrderingScreen.class);
                newIntent.putExtra(getResources().getString(R.string.strTestVersion), version);
                startActivity(newIntent);
            }
        });
    }
}
