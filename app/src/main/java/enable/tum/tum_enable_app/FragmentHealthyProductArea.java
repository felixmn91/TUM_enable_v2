package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    private ArrayList<ImageButton> imgButtons;

    private LinearLayout healthyProductContainer;

    private RelativeLayout healthyProductArea;

    private OnHealthyProductSelectedListener onHealthyProductSelectedListener;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState)
    {
        super.onCreate(saverdInstacesState);

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Log.d(Tag, "done");
                ImageView s = (ImageView) getActivity().findViewById(R.id.scrollIndicatorRight);

                fadeOut(s);

            }
        }.start();


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
            cBtn.setIdentifier(p.getpPathPicture());
            cBtn.setProductName(p.getName());
            cBtn.setPrice(p.getPrice());
            cBtn.setOnClickListener(this);
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

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        TestVersion testVersion = (TestVersion) getActivity().getIntent().getSerializableExtra(getResources().getString(R.string.strTestVersion));

        onHealthyProductSelectedListener = (OnHealthyProductSelectedListener) getActivity();
        healthyProductContainer = (LinearLayout) getActivity().findViewById(R.id.healthyProductContainer);

        healthyProductArea= (RelativeLayout) getActivity().findViewById(R.id.healthyProductArea);
        if (testVersion == TestVersion.avatar_off_nudging_on || testVersion == TestVersion.avatar_on_nudging_on)
        {
            healthyProductArea.setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbackrounds));

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

    public void fadeOut(ImageView v){
        Animation fadeOut = new AlphaAnimation(1, 0);  // the 1, 0 here notifies that we want the opacity to go from opaque (1) to transparent (0)
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(500); // Start fading out after 500 milli seconds
        fadeOut.setDuration(1000); // Fadeout duration should be 1000 milli seconds

        Log.d(Tag, "fade");

        v.startAnimation(fadeOut);
        v.setVisibility(View.INVISIBLE);

    }


}
