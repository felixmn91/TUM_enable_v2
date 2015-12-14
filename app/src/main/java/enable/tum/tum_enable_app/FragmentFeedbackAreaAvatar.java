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
public class FragmentFeedbackAreaAvatar extends Fragment implements IOrderObserver {

    private static final String TAG = "FeedbackAreaAvatar";

    ImageView avatar;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {

        super.onCreate(saverdInstacesState);
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area_avatar, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        avatar = (ImageView) getActivity().findViewById(R.id.imageAvatar);

        ProgramLogicSingleton.getInstance().registerAsObserver(this);
    }

    @Override
    public void onOrderChange()
    {
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        double kcal = instance.getKcalOfOrder();



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
