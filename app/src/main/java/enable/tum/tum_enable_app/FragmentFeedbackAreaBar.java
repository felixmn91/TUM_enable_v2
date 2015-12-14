package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class FragmentFeedbackAreaBar extends Fragment implements IOrderObserver {
    private TextView mVersionField;

    private FeedbackBarView feedbackBar;
    private TextView txtViewActualKcal;

    //onCreate only Configures the fragment instance
    @Override
    public void onCreate(Bundle saverdInstacesState) {

        super.onCreate(saverdInstacesState);
    }


    //You Inflate Fragment in onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area_bar, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentFeedbackAreaBar", "bla");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        txtViewActualKcal = (TextView) getActivity().findViewById(R.id.txtOben);

        feedbackBar = (FeedbackBarView) getActivity().findViewById(R.id.feedbackbar);
        feedbackBar.setSpectrumMaxValue(1600);
        feedbackBar.setSpectrumThresholdValue(800);
        feedbackBar.calculateValues();
        feedbackBar.setSpectrumActualValue(0);

        // IOrderObservable observable = (IOrderObservable) getParentFragment();
        IOrderObservable observable = (IOrderObservable) ProgramLogicSingleton.getInstance();
        observable.registerAsObserver(this);
    }

    @Override
    public void onOrderChange()
    {
        double actualKcal = ProgramLogicSingleton.getInstance().getKcalOfOrder();
        feedbackBar.setSpectrumActualValue((float) actualKcal);

        txtViewActualKcal.setText("Kcal: " + (int)actualKcal);
    }
}
