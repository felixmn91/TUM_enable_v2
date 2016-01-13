package enable.tum.tum_enable_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import enable.tum.tum_enable_app.ProductHandling.Category;

public class ActivityOrderingScreen extends FragmentActivity
        implements FragmentHealthyProductArea.OnHealthyProductSelectedListener,
        FragmentUnhealthyProductArea.OnUnhealthyProductSelectedListener {

    private static final String TAG = "LaunchActivity";

    private Fragment fragmentFeedbackArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_ordering_screen);

        Intent i = getIntent();
        TestVersions version = (TestVersions) i.getSerializableExtra(getResources().getString(R.string.strTestVersion));
        Log.d(TAG, version + "");

        FragmentManager fm = getFragmentManager();

        fragmentFeedbackArea = fm.findFragmentById(R.id.fragmentContainer_feedback_area);
        if (fragmentFeedbackArea == null) {
            fragmentFeedbackArea = new FragmentFeedbackArea();
            fm.beginTransaction().add(R.id.fragmentContainer_feedback_area, fragmentFeedbackArea).commit();
        }

        Fragment fragmentHealthyProductArea = fm.findFragmentById(R.id.fragmentContainer_healthy_product_area);
        if (fragmentHealthyProductArea == null) {
            fragmentHealthyProductArea = new FragmentHealthyProductArea();
            fm.beginTransaction().add(R.id.fragmentContainer_healthy_product_area, fragmentHealthyProductArea).commit();
        }

        Fragment fragmentUnhealthyProductArea = fm.findFragmentById(R.id.fragmentContainer_unhealthy_product_area);
        if (fragmentUnhealthyProductArea == null) {
            fragmentUnhealthyProductArea = new FragmentUnhealthyProductArea();
            fm.beginTransaction().add(R.id.fragmentContainer_unhealthy_product_area, fragmentUnhealthyProductArea).commit();
        }
    }

    @Override
    public void onHealthyProductSelected(int id) {
        if (fragmentFeedbackArea != null && fragmentFeedbackArea instanceof OnIncomingOrderListener) {
            ((OnIncomingOrderListener) fragmentFeedbackArea).onIncomingOrder(id, Category.healthy);
        }
    }

    @Override
    public void onUnhealthyProductSelected(int id) {
        if (fragmentFeedbackArea != null && fragmentFeedbackArea instanceof OnIncomingOrderListener) {
            ((OnIncomingOrderListener) fragmentFeedbackArea).onIncomingOrder(id, Category.unhealthy);
        }
    }

    public interface OnIncomingOrderListener {
        public void onIncomingOrder(int id, Category category);
    }

}
