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

import enable.tum.tum_enable_app.ProductHandling.Product;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

import java.util.ArrayList;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentUnhealthyProductArea extends Fragment implements View.OnClickListener {
    public static final String Tag = "FragmentUHPA";

    private TextView mVersionField;

    private ArrayList<ImageButton> imgButtons;

    private LinearLayout unhealthyProductContainer;

    private OnUnhealthyProductSelectedListener onUnhealthyProductSelectedListener;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {
        super.onCreate(saverdInstacesState);
    }

    private void initializeImgButtonsRow() {
        imgButtons = new ArrayList<>();

        ArrayList<Product> unhealthyProducts = ProgramLogicSingleton.getInstance().getUnhealthyProducts();
        int i = 0;
        for (Product p : unhealthyProducts) {
            CustomButtonProductItem cBtn = new CustomButtonProductItem(getActivity());
            cBtn.setIdentifier(p.getPathPicture());
            cBtn.setProductName(p.getName());
            cBtn.setPrice(p.getPrice());
            cBtn.setOnClickListener(this);
            cBtn.setBackgroundResourceIdentifier(R.drawable.background_custom_button_unhealthy_product_item);
            cBtn.setId(i);

            unhealthyProductContainer.addView(cBtn);

            i++;
        }
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_unhealthy_product_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d(Tag, "in onCreateView");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onUnhealthyProductSelectedListener = (OnUnhealthyProductSelectedListener) getActivity();

        unhealthyProductContainer = (LinearLayout) getActivity().findViewById(R.id.unhealthyProductContainer);
        initializeImgButtonsRow();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        onUnhealthyProductSelectedListener.onUnhealthyProductSelected(id);

        Log.d(Tag, "Id: " + id);
    }

    public interface OnUnhealthyProductSelectedListener {
        public void onUnhealthyProductSelected(int id);
    }
}
