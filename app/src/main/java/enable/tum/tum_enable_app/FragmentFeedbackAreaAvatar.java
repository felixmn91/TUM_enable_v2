package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import enable.tum.tum_enable_app.ProductHandling.Category;
import enable.tum.tum_enable_app.ProductHandling.Product;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Lennart Mittag on 06.12.2015.
 */
public class FragmentFeedbackAreaAvatar extends Fragment implements ActivityOrderingScreen.OnIncomingOrderListener {

    private static final String TAG = "FeedbackAreaAvatar";

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {

        super.onCreate(saverdInstacesState);
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area_avatar, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentFeedbackAreaAv", "bla");
        return v;
    }

    @Override
    public void onIncomingOrder(int id, Category category) {
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        ArrayList<Product> actualOrder = instance.getOrder();

        // double kcal = instance.getKcalOfOrder();
        double kcal = 350d;
        ImageView avatar = (ImageView) getActivity().findViewById(R.id.imageAvatar);

        if (kcal < 800) {
            avatar.setImageResource(R.drawable.smiley_great);
        } else if (kcal >= 800 && kcal < 900) {
            avatar.setImageResource(R.drawable.smiley_good);
        } else if (kcal >= 900 && kcal < 1000) {
            avatar.setImageResource(R.drawable.smiley_ok);
        } else {
            avatar.setImageResource(R.drawable.smiley_bad);
        }
    }
}
