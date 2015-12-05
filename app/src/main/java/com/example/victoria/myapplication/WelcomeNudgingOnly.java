package com.example.victoria.myapplication;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by victoria on 01.12.15.
 */
public class WelcomeNudgingOnly extends Fragment{

    private TextView mVersionField;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState){

        super.onCreate(saverdInstacesState
        );
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.welcome_screen_nudging_only_version, container, false); //a true would show the layout now, we are doing this in the ActivityOrderingScreen
        mVersionField = (TextView) v.findViewById(R.id.VersionText);
        mVersionField.setText("Nudging Only");

        Log.d("WelcomeNudgingOnly", "Nudging Only");
        return v;
    }
}
