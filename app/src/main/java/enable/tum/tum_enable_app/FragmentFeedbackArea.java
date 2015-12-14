package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import enable.tum.tum_enable_app.ProductHandling.Category;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by victoria on 01.12.15.
 */
public class FragmentFeedbackArea extends Fragment implements ActivityOrderingScreen.OnIncomingOrderListener, IOrderObserver, IOrderObservable
{
    private TextView mVersionField;

    private List<IOrderObserver> registeredObserver;

    private Fragment fragmentFeedbackAreaBar;
    private Fragment fragmentFeedbackAreaTablet;
    private Fragment fragmentFeedbackAreaAvatar;

    private ActivityOrderingScreen.OnIncomingOrderListener onIncomingOrderListener;

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
        View v = inflater.inflate(R.layout.layout_fragment_feedback_area, container, false); //a true would show the layout now, we are doing thsi in the ActivityOrderingScreen

        Log.d("FragmentFeedbackArea", "Standard Version");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        registeredObserver = new LinkedList<>();
        ProgramLogicSingleton.getInstance().registerAsObserver(this);

        // Initialize and start Fragments of the top row
        FragmentManager fm = getFragmentManager();

        fragmentFeedbackAreaBar = fm.findFragmentById(R.id.layout_nestedFragment_feedback_bar);
        if (fragmentFeedbackAreaBar == null)
        {
            fragmentFeedbackAreaBar = new FragmentFeedbackAreaBar();
            fm.beginTransaction().add(R.id.layout_nestedFragment_feedback_bar, fragmentFeedbackAreaBar).commit();
        }

        fragmentFeedbackAreaTablet = fm.findFragmentById(R.id.layout_nestedFragment_tablet);
        if (fragmentFeedbackAreaTablet == null)
        {
            fragmentFeedbackAreaTablet = new FragmentFeedbackAreaTablet();
            fm.beginTransaction().add(R.id.layout_nestedFragment_tablet, fragmentFeedbackAreaTablet).commit();
        }

        fragmentFeedbackAreaAvatar = fm.findFragmentById(R.id.layout_nestedFragment_avatar);
        if (fragmentFeedbackAreaAvatar == null)
        {
            fragmentFeedbackAreaAvatar = new FragmentFeedbackAreaAvatar();
            fm.beginTransaction().add(R.id.layout_nestedFragment_avatar, fragmentFeedbackAreaAvatar).commit();
        }

    }

    @Override
    public void onIncomingOrder(int id, Category category)
    {
        if (fragmentFeedbackAreaTablet != null && fragmentFeedbackAreaTablet instanceof ActivityOrderingScreen.OnIncomingOrderListener)
        {
            ((ActivityOrderingScreen.OnIncomingOrderListener) fragmentFeedbackAreaTablet).onIncomingOrder(id, category);
            ((ActivityOrderingScreen.OnIncomingOrderListener) fragmentFeedbackAreaAvatar).onIncomingOrder(id, category);
        }
    }

    @Override
    public void registerAsObserver(IOrderObserver observer)
    {
        registeredObserver.add(observer);
    }

    @Override
    public void informObserverChangeHasOccurred()
    {
        for (IOrderObserver orderObserver : registeredObserver)
        {
            orderObserver.onOrderChange();
        }
    }

    @Override
    public void onOrderChange()
    {
        informObserverChangeHasOccurred();
    }
}