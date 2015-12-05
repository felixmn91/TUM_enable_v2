package com.example.victoria.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by victoria on 01.12.15.
 */
public class FragmentFeedbackArea extends Fragment {
    private TextView mVersionField;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {
        super.onCreate(saverdInstacesState);
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentFeedbackArea", "Standard Version");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getFragmentManager();

        Fragment fragmentFeedbackAreaBar = fm.findFragmentById(R.id.layout_nestedFragment_feedback_bar);
        if (fragmentFeedbackAreaBar == null) {
            fragmentFeedbackAreaBar = new FragmentFeedbackAreaBar();
            fm.beginTransaction().add(R.id.layout_nestedFragment_feedback_bar, fragmentFeedbackAreaBar).commit();
        }

        Fragment fragmentFeedbackAreaTablet = fm.findFragmentById(R.id.layout_nestedFragment_tablet);
        if (fragmentFeedbackAreaTablet == null) {
            fragmentFeedbackAreaTablet = new FragmentFeedbackAreaTablet();
            fm.beginTransaction().add(R.id.layout_nestedFragment_tablet, fragmentFeedbackAreaTablet).commit();
        }

    }
}