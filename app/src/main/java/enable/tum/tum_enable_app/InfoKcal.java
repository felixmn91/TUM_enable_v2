package enable.tum.tum_enable_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;

/**
 * Created by Felix Naser on 14.01.2016.
 */
public class InfoKcal extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Die Deutsche Gesellschaft für Ernährung empfiehlt:\n" +
                "Für Männer (1,80m; 78kg): 750kcal\n" +
                "Für Frauen (1,71m; 69kg): 650kcal\n" +
                "pro Mahlzeit")
                .setNegativeButton("Ok, vielen Dank.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
