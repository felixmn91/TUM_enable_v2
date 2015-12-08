package com.example.victoria.myapplication;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.victoria.myapplication.ProductHandling.Category;
import com.example.victoria.myapplication.ProductHandling.Product;
import com.example.victoria.myapplication.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentFeedbackAreaTablet extends Fragment
        implements View.OnClickListener,
        ActivityOrderingScreen.OnIncomingOrderListener
{
    private static final String Tag = "FragmentFAT";

    private LinearLayout feedback_area_tabletContainer;

    private boolean[] actualTablet = new boolean[6];

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
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area_tablet, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        for (int i = 0; i < actualTablet.length; i++)
        {
            actualTablet[i] = false;
        }

        Log.d("FragmentFeedbackAreaBar", "bla");
        return v;
    }

    @Override
    public void onIncomingOrder(int id, Category category)
    {
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();

        Product product = null;

        switch (category)
        {
            case healthy:
                product = instance.getHealthyProducts().get(id);
                break;
            case unhealthy:
                product = instance.getUnhealthyProducts().get(id);
                break;
        }

        ImageButton btnImg = null;
        /*ImageButton btnImg = new ImageButton(getActivity());
        btnImg.setImageResource(product.getPathPicture());
        btnImg.setAdjustViewBounds(true);
        btnImg.setOnClickListener(this);
        btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        btnImg.setId(0);*/

        if (!actualTablet[0])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button);
            actualTablet[0] = true;
        } else if (!actualTablet[1])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button2);
            actualTablet[1] = true;
        } else if (!actualTablet[2])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button3);
            actualTablet[2] = true;
        } else if (!actualTablet[3])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button4);
            actualTablet[3] = true;
        } else if (!actualTablet[4])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button5);
            actualTablet[4] = true;
        } else if (!actualTablet[5])
        {
            btnImg = (ImageButton) getActivity().findViewById(R.id.button6);
            actualTablet[5] = true;
        } else
        {
            return;
        }

        btnImg.setOnClickListener(this);

        btnImg.setImageResource(product.getPathPicture());
        btnImg.setBackgroundColor(Color.GRAY);
        btnImg.invalidate();

        /*if(feedback_area_tabletContainer == null) {
            feedback_area_tabletContainer = (LinearLayout) getActivity().findViewById(R.id.feedback_area_tabletContainer);
        }

        feedback_area_tabletContainer.removeAllViewsInLayout();
        feedback_area_tabletContainer.addView(btnImg);*/
    }

    @Override
    public void onClick(View v)
    {
        // feedback_area_tabletContainer.removeView(v);
        ImageButton imgBtn = (ImageButton) v;
        imgBtn.setImageResource(R.drawable.mcdonalds_golden_arches);
        imgBtn.setBackgroundColor(Color.BLACK);
        imgBtn.setOnClickListener(null);
        imgBtn.invalidate();

        if (v.getId() == R.id.button)
        {
            actualTablet[0] = false;
        } else if (v.getId() == R.id.button2)
        {
            actualTablet[1] = false;
        } else if (v.getId() == R.id.button3)
        {
            actualTablet[2] = false;
        } else if (v.getId() == R.id.button4)
        {
            actualTablet[3] = false;
        } else if (v.getId() == R.id.button5)
        {
            actualTablet[4] = false;
        } else if (v.getId() == R.id.button6)
        {
            actualTablet[5] = false;
        }
    }
}
