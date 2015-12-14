package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    private void initializeImgButtonsRow()
    {
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
            cBtn.setBackgroundResourceIdentifier(R.drawable.background_custom_button_healthy_product_item);
            cBtn.setId(i);
            cBtn.setTag(p);

            healthyProductContainer.addView(cBtn);

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

    // Interface, das die Kommunikation des Fragments mit der Activity ermöglicht, wenn auf ein
    // Produkt geklickt wurde
    public interface OnHealthyProductSelectedListener
    {
        public void onHealthyProductSelected(int id);
    }
}
