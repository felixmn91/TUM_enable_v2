package com.example.victoria.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class ActivityOrderingScreen extends FragmentActivity implements FragmentHealthyProductArea.OnHealthyProductSelectedListener
{

    private static final String SELECTED_VERSION = "com.example.android.selected_version";
    private static final String TAG = "LaunchActivity";
    private TextView mVersionTextView;
    private String mSelectedVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_ordering_screen);

        mSelectedVersion = getIntent().getStringExtra("SELECTED_VERSION");

        FragmentManager fm = getFragmentManager();

        Fragment fragmentFeedbackArea = fm.findFragmentById(R.id.fragmentContainer_feedback_area);
        if (fragmentFeedbackArea == null) {
            fragmentFeedbackArea = new FragmentFeedbackArea();
            fm.beginTransaction().add(R.id.fragmentContainer_feedback_area, fragmentFeedbackArea).commit();
        }

        Fragment fragmentHealthyProductArea = fm.findFragmentById(R.id.fragmentContainer_healthy_product_area);
        if (fragmentHealthyProductArea == null) {
            fragmentHealthyProductArea = new FragmentHealthyProductArea();
            fm.beginTransaction().add(R.id.fragmentContainer_healthy_product_area, fragmentHealthyProductArea).commit();
        }

        Fragment fragmentUnhealthyProductArea = fm.findFragmentById(R.id.fragmentContainer_unhealthy_product_area);
        if (fragmentUnhealthyProductArea == null) {
            fragmentUnhealthyProductArea = new FragmentUnhealthyProductArea();
            fm.beginTransaction().add(R.id.fragmentContainer_unhealthy_product_area, fragmentUnhealthyProductArea).commit();
        }
    }

    public void onHealthyProductSelected(int id)
    {

    }
}
