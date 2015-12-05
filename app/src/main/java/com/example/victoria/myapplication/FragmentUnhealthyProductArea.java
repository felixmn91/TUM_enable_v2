package com.example.victoria.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentUnhealthyProductArea extends Fragment
{
    private TextView mVersionField;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState)
    {

        super.onCreate(saverdInstacesState);
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
    {
        View v = inflater.inflate(R.layout.layout_unhealthy_product_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentHPArea", "Standard Version");
        return v;
    }
}
