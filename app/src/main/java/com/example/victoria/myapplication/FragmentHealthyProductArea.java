package com.example.victoria.myapplication;

import android.app.Fragment;
import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentHealthyProductArea extends Fragment implements View.OnClickListener
{
    public static final String Tag = "FragmentHPA";

    private TextView mVersionField;

    private ArrayList<ImageButton> imgButtons;

    private LinearLayout healthyProductContainer;

    private OnHealthyProductSelectedListener onHealthyProductSelectedListener;


    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState)
    {
        super.onCreate(saverdInstacesState);
    }

    private void initializeImgButtonsRow()
    {
        imgButtons = new ArrayList<>();

        ArrayList<Product> healthyProducts = ProgramLogicSingleton.getInstance().getHealthyProducts();
        int i = 0;
        for (Product p : healthyProducts)
        {
            ImageButton btnImg = new ImageButton(getActivity());
            btnImg.setImageResource(p.getPathPicture());
            btnImg.setAdjustViewBounds(true);
            btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            btnImg.setOnClickListener(this);
            btnImg.setId(i);

            if (btnImg == null)
            {
                Log.d(Tag, "Button ist null");
            }

            if (healthyProductContainer == null)
            {
                Log.d(Tag, "healthyProductContainer ist null");
            }

            healthyProductContainer.addView(btnImg);
            i++;
        }
    }

    private void initializeImgButtonsRow2() {
        imgButtons = new ArrayList<>();

        ArrayList<Product> healthyProducts = ProgramLogicSingleton.getInstance().getHealthyProducts();
        int i = 0;
        for (Product p : healthyProducts)
        {
            ImageButton btnImg = new ImageButton(getActivity());
            btnImg.setImageResource(p.getPathPicture());
            btnImg.setAdjustViewBounds(false);
            btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            btnImg.setOnClickListener(this);
            btnImg.setId(i);

            healthyProductContainer.addView(btnImg);

            i++;
        }
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
    {
        View v = inflater.inflate(R.layout.layout_healthy_product_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d(Tag, "in onCreateView");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        onHealthyProductSelectedListener = (OnHealthyProductSelectedListener) getActivity();

        healthyProductContainer = (LinearLayout) getActivity().findViewById(R.id.healthyProductContainer);
        initializeImgButtonsRow();
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        onHealthyProductSelectedListener.onHealthyProductSelected(id);

        Log.d(Tag, "Id: " + id);
    }

    public interface OnHealthyProductSelectedListener
    {
        public void onHealthyProductSelected(int id);
    }
}
