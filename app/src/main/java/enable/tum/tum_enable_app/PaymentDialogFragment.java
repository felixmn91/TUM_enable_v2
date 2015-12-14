package enable.tum.tum_enable_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import enable.tum.tum_enable_app.ProductHandling.Product;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Felix Naser on 14.12.2015.
 */
public class PaymentDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Ihre Bestellung:\n" + listAllProducts())
                .setPositiveButton("bestätigen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private String listAllProducts() {
        String list = "";
        ProgramLogicSingleton s = ProgramLogicSingleton.getOurInstance();

        for (Product p : s.getOrder()) {
            list += p.getName() + "\n";
        }

        list += s.getPriceOfOrder() + "\u20ac";

        return list;
    }
}