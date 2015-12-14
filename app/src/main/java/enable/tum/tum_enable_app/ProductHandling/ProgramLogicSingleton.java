package enable.tum.tum_enable_app.ProductHandling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enable.tum.tum_enable_app.IOrderObservable;
import enable.tum.tum_enable_app.IOrderObserver;
import enable.tum.tum_enable_app.R;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class ProgramLogicSingleton implements IOrderObservable {
    private static ProgramLogicSingleton ourInstance = new ProgramLogicSingleton();

    private List<IOrderObserver> registeredObservers;

    private ArrayList<Product> healthyProducts;
    private ArrayList<Product> unhealthyProducts;
    private ArrayList<Product> order;

    private double actualPriceOfOrder = 0d;
    private double actualKcalOfOrder = 0d;

    private ProgramLogicSingleton() {
        initializeArrays();
        registeredObservers = new LinkedList<>();
    }

    public static ProgramLogicSingleton getInstance() {
        return ourInstance;
    }

    // TODO Methode entfernen
    public ArrayList<Product> getOrder() {
        return order;
    }

    public double getKcalOfOrder() {
        return actualKcalOfOrder;
    }

    public String getKcalOfOrderAsFormattedString() {
        return String.format("%.2f", actualKcalOfOrder);
    }

    public double getPriceOfOrder() {
        return actualPriceOfOrder;
    }

    public String getPriceOfOrderAsFormattedString() {
        // return Math.round(actualPriceOfOrder * 100.0) / 100.0 + "";
        return String.format("%.2f", actualPriceOfOrder);
    }

    public void addProductToActualOrder(Product product) {
        if (order.size() < 6) {
            order.add(product);
        }

        updateActualOrderPrice();
        updateActualOrderKcal();

        informObserverChangeHasOccurred();
    }

    public void removeProductFromActualOrder(Product product) {
        if (order.contains(product)) {
            order.remove(product);
        }

        updateActualOrderPrice();
        updateActualOrderKcal();

        informObserverChangeHasOccurred();
    }

    private void updateActualOrderPrice() {
        actualPriceOfOrder = 0d;
        for (Product p : order) {
            actualPriceOfOrder += p.getPrice();
        }
    }

    private void updateActualOrderKcal() {
        actualKcalOfOrder = 0d;
        for (Product p : order) {
            actualKcalOfOrder += p.getKcal();
        }
    }

    private void initializeArrays() {
        order = new ArrayList<>();

        healthyProducts = new ArrayList<>();

        // Hauptspeisen
        healthyProducts.add(new Product("Big Salad Quinoa Bratling", 5.19, 210.0, Category.healthy, R.drawable.product_big_salad_quinoa_product_preview));
        healthyProducts.add(new Product("Cheeseburger", 1.10, 301.0, Category.healthy, R.drawable.product_cheeseburger_product_preview));
        healthyProducts.add(new Product("Chicken Nuggets", 3.19, 268.0, Category.healthy, R.drawable.product_chicken_mcnuggets_product_preview));

        // Beilagen
        healthyProducts.add(new Product("Salat klein", 3.19, 16.0, Category.healthy, R.drawable.product_snack_salat_classic_product_preview));
        healthyProducts.add(new Product("Salat groß", 3.19, 26.0, Category.healthy, R.drawable.product_big_salad_product_preview));
        healthyProducts.add(new Product("Pommes klein", 3.19, 239.0, Category.healthy, R.drawable.product_pommes_frites_klein_product_preview));

        // Getränke
        healthyProducts.add(new Product("Bon Aqa", 3.19, 0.0, Category.healthy, R.drawable.product_bonaqa_product_preview));
        healthyProducts.add(new Product("Coca Cola Light", 1.49, 1.0, Category.healthy, R.drawable.product_coca_cola_light_product_preview));
        healthyProducts.add(new Product("Apfelsaftschorle", 3.19, 100.0, Category.healthy, R.drawable.product_lift_apfelschorle_product_preview));

        unhealthyProducts = new ArrayList<>();

        // Hauptspeisen
        unhealthyProducts.add(new Product("Big Mac", 4.19, 509.0, Category.unhealthy, R.drawable.product_big_mac_product_preview));
        unhealthyProducts.add(new Product("McVeggi TS", 4.19, 465.0, Category.unhealthy, R.drawable.product_mcveggie_ts_product_preview));
        unhealthyProducts.add(new Product("Hamburger Royal TS", 4.19, 518.0, Category.unhealthy, R.drawable.product_hamburger_royal_ts_product_preview));

        // Beilagen
        unhealthyProducts.add(new Product("Pommes mittel", 4.19, 341.0, Category.unhealthy, R.drawable.product_pommes_frites_mittel_product_preview));
        unhealthyProducts.add(new Product("Pommes groß", 4.19, 448.0, Category.unhealthy, R.drawable.product_pommes_frites_gross_product_preview));
        unhealthyProducts.add(new Product("McFlurry Smarties", 4.19, 350.0, Category.unhealthy, R.drawable.product_mcflurry__smarties__product_preview));

        // Getränke
        unhealthyProducts.add(new Product("Coca Cola mittel", 1.49, 170.0, Category.unhealthy, R.drawable.product_coca_cola_product_preview));
        unhealthyProducts.add(new Product("Coca Cola groß", 4.19, 210.0, Category.unhealthy, R.drawable.product_coca_cola_product_preview));
        unhealthyProducts.add(new Product("Sprite mittel", 4.19, 148.0, Category.unhealthy, R.drawable.product_sprite_product_preview));
        unhealthyProducts.add(new Product("Sprite groß", 4.19, 185.0, Category.unhealthy, R.drawable.product_sprite_product_preview));
    }

    public ArrayList<Product> getUnhealthyProducts() {
        return unhealthyProducts;
    }

    public ArrayList<Product> getHealthyProducts() {
        return healthyProducts;
    }

    @Override
    public void registerAsObserver(IOrderObserver observer) {
        registeredObservers.add(observer);
    }

    private void informObserverChangeHasOccurred() {
        for (IOrderObserver orderObserver : registeredObservers) {
            orderObserver.onOrderChange();
        }
    }

    public void resetProgrammLogicSingleton() {
        ourInstance = new ProgramLogicSingleton();
    }
}
