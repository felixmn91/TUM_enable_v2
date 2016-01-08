package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import enable.tum.tum_enable_app.ProductHandling.Product;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

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

        //final HorizontalScrollView hs = (HorizontalScrollView) getActivity().findViewById(R.id.scrollView);

        //Log.d(Tag, Integer.toString(hs.getScrollX()));
    }

    private void initializeImgButtonsRow(TestVersion testVersion)
    {
        Log.d(Tag, "" + testVersion);
        imgButtons = new ArrayList<>();

        ArrayList<Product> healthyProducts = ProgramLogicSingleton.getInstance().getHealthyProducts();
        int i = 0;
        for (Product p : healthyProducts)
        {
            CustomButtonProductItem cBtn = new CustomButtonProductItem(getActivity());
            cBtn.setIdentifier(p.getPathPicture());
            cBtn.setProductName(p.getName());
            cBtn.setPrice(p.getPrice());
            cBtn.setOnClickListener(this);
            if (testVersion == TestVersion.avatar_off_nudging_on || testVersion == TestVersion.avatar_on_nudging_on)
            {
                cBtn.setBackgroundResourceIdentifier(R.drawable.background_custom_button_healthy_product_item);
            } else
            {
                cBtn.setBackgroundResourceIdentifier(R.drawable.background_custom_button_unhealthy_product_item);
            }
            cBtn.setId(i);
            cBtn.setTag(p);

            healthyProductContainer.addView(cBtn);

            i++;
        }
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstaceState)
    {
        View v = inflater.inflate(R.layout.layout_healthy_product_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d(Tag, "in onCreateView");

      //  View SS = getActivity().findViewById(R.id.scrollView);
      //  Log.d(Tag, Integer.toString(SS.getScrollX()));
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        TestVersion testVersion = (TestVersion) getActivity().getIntent().getSerializableExtra(getResources().getString(R.string.strTestVersion));

        onHealthyProductSelectedListener = (OnHealthyProductSelectedListener) getActivity();

        healthyProductContainer = (LinearLayout) getActivity().findViewById(R.id.healthyProductContainer);
        if (testVersion == TestVersion.avatar_off_nudging_on || testVersion == TestVersion.avatar_on_nudging_on)
        {
            healthyProductContainer.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkgreen));
        } else
        {
            healthyProductContainer.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
        }

                initializeImgButtonsRow(testVersion);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        onHealthyProductSelectedListener.onHealthyProductSelected(id);

        Log.d(Tag, "Id: " + id);
    }

    // Interface, das die Kommunikation des Fragments mit der Activity ermöglicht, wenn auf ein
    // Produkt geklickt wurde
    public interface OnHealthyProductSelectedListener
    {
        public void onHealthyProductSelected(int id);
    }
}
