package enable.tum.tum_enable_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import enable.tum.tum_enable_app.ProductHandling.Product;
import enable.tum.tum_enable_app.ProductHandling.ProgramLogicSingleton;
import enable.tum.tum_enable_app.ProductHandling.TestRunData;

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

                        writeTestRunDataToFile();

                        ProgramLogicSingleton.getInstance().resetProgrammLogicSingleton();

                        Intent newIntent = new Intent(getActivity(), ActivityGoodbyeScreen.class);
                        startActivity(newIntent);
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
        ProgramLogicSingleton s = ProgramLogicSingleton.getInstance();

        for (Product p : s.getOrder()) {
            list += p.getName() + "\n";
        }

        list += s.getPriceOfOrderAsFormattedString() + "\u20ac";

        return list;
    }

    private void writeTestRunDataToFile() {
        String filename = getResources().getString(R.string.filename);

        ProgramLogicSingleton instance = ProgramLogicSingleton.getInstance();
        TestRunData test = new TestRunData(instance.getOrder());

        String string = test.testRunDataToString();

        //This will get the SD Card directory and create a folder named MyFiles in it.
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/TestRunData");
        directory.mkdirs();

        //Now create the file in the above directory and write the contents into it
        File file = new File(directory, getActivity().getResources().getString(R.string.filename));
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append("\n" + string);

            osw.flush();
            osw.close();
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}