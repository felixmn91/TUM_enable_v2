package com.example.victoria.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.victoria.myapplication.com.example.victoria.ProductHandling.Product;
import com.example.victoria.myapplication.com.example.victoria.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentFeedbackAreaTablet extends Fragment
        implements ActivityOrderingScreen.OnIncomingOrderListener,
        View.OnClickListener
{
    private static final String Tag = "FragmentFAT";

    private LinearLayout feedback_area_tabletContainer;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {

        super.onCreate(saverdInstacesState);
    }

    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area_tablet, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentFeedbackAreaBar", "bla");
        return v;
    }

    @Override
    public void onIncomingOrder(int id)
    {
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        Product p = instance.getHealthyProducts().get(id);

        ImageButton btnImg = new ImageButton(getActivity());
        btnImg.setImageResource(p.getPathPicture());
        btnImg.setAdjustViewBounds(true);
        btnImg.setOnClickListener(this);
        btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        btnImg.setId(0);

        if(feedback_area_tabletContainer == null) {
            feedback_area_tabletContainer = (LinearLayout) getActivity().findViewById(R.id.feedback_area_tabletContainer);
        }

        feedback_area_tabletContainer.removeAllViewsInLayout();
        feedback_area_tabletContainer.addView(btnImg);
    }

    @Override
    public void onClick(View v)
    {
        feedback_area_tabletContainer.removeView(v);
    }
}
