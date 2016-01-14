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

    private ImageView avatar;
    private TextView comment;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        avatar = (ImageView) getActivity().findViewById(R.id.imageAvatar);
        avatar.setImageResource(R.drawable.a_ok);

        comment = (TextView) getActivity().findViewById(R.id.comment);
        comment.setText("Drücke auf das Bild um ein Produkt auszuwählen!");

        ProgramLogicSingleton.getInstance().registerAsObserver(this);
    }

    @Override
    public void onOrderChange() {
        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        double kcal = instance.getKcalOfOrder();

        double goal = instance.getPlanedkcalIntake();

        if (kcal == 0) {
            avatar.setImageResource(R.drawable.a_ok);
            comment.setText("Drücke auf das Bild um ein Produkt auszuwählen!");
        }
        if (kcal > 0 && kcal <= goal) {
            avatar.setImageResource(R.drawable.a_happy);
            comment.setText("Super Wahl!");

        } else if (kcal > goal && kcal <= (1.5 * goal)) {
            avatar.setImageResource(R.drawable.a_ok);
            comment.setText("Ist heute etwa Cheat Day?");

        } else if (kcal > (3 / 2 * goal) && kcal <= (2 * goal)) {
            avatar.setImageResource(R.drawable.a_not_ok);
            comment.setText("Bist du dir wirklich sicher?");
        } else if (kcal > (2 * goal)) {
            avatar.setImageResource(R.drawable.a_sad);
            comment.setText("Nochmal überlegen?");
        }
    }
}
