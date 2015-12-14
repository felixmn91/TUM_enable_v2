package enable.tum.tum_enable_app.ProductHandling;

import java.util.ArrayList;

/**
 * Created by Felix Naser on 14.12.2015.
 */
public class TestrunData {

    private static int id = 0;

    private ArrayList<Product> order;
    // private int id;

    public TestrunData(ArrayList<Product> order) {
        this.order = order;
        id++;
    }

    public String testrunDataToString() {
        String result = "Testdata: " + id + "\n";
        double preis = 0;
        double kcal = 0;

        for (Product p : order) {
            result += p.getName() + "\n";
            preis += p.getPrice();
            kcal += p.getKcal();
        }

        result += "Gesamtpreis: " + preis + "\n";
        result += "Gesamt Kcal: " + kcal + "\n\n";

        return result;
    }
}
