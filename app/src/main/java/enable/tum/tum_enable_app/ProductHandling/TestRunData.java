package enable.tum.tum_enable_app.ProductHandling;

import java.util.ArrayList;

/**
 * Created by Felix Naser on 14.12.2015.
 */
public class TestRunData {

    private static int id = 0;

    private ArrayList<Product> order;

    public TestRunData(ArrayList<Product> order) {
        this.order = order;
        id++;
    }

    public String testRunDataToString() {
        String result = "Testdata: " + id + "\n";
        double preis = 0;
        double kcal = 0;

        for (Product p : order) {
            result += p.getName() + "\n";
            preis += p.getPrice();
            kcal += p.getKcal();
        }

        result += "Gesamtpreis: " + String.format("%.2f", preis) + "\n";
        result += "Gesamt Kcal: " + String.format("%.2f", kcal) + "\n\n";

        return result;
    }
}
