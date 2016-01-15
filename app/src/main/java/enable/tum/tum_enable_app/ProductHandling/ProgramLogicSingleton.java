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
    private double planedkcalIntake= 600d;

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

    public double getPlanedkcalIntake() {return planedkcalIntake; }

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

    public void updateplanedkcalIntake(double kcal){
        planedkcalIntake = kcal;
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
        healthyProducts.add(new Product("McWrap Chicken", 3.99, 485.0, Category.healthy, R.drawable.product_mcwrap_chicken, R.drawable.produktinfo_cola_05));
        healthyProducts.add(new Product("Big Salad Quinoa", 4.49, 210.0, Category.healthy, R.drawable.product_big_salad_quinoa_product_preview, R.drawable.produktinfo_salat_quinoa));
        healthyProducts.add(new Product("Cheeseburger", 1.19, 304.0, Category.healthy, R.drawable.product_cheeseburger_product_preview_quadrat, R.drawable.produktinfo_cheesburger));
        healthyProducts.add(new Product("6 Chicken McNuggets", 3.29, 268.0, Category.healthy, R.drawable.product_chicken_mcnuggets_product_preview, R.drawable.produktinfo_nuggets));

        // Beilagen
        healthyProducts.add(new Product("Salat klein", 1.49, 16.0, Category.healthy, R.drawable.product_snack_salat_classic_product_preview_quadrat, R.drawable.produktinfo_snack_salat));
        healthyProducts.add(new Product("Salat groß", 3.49, 26.0, Category.healthy, R.drawable.product_big_salad_product_preview, R.drawable.produktinfo_salat));
        healthyProducts.add(new Product("Pommes klein", 1.89, 210.0, Category.healthy, R.drawable.product_pommes_frites_klein_product_preview,R.drawable.produktinfo_salat_quinoa));

        // Getränke
        healthyProducts.add(new Product("Bonaqa 0.4L", 1.99, 0.0, Category.healthy, R.drawable.product_bonaqa_product_preview, R.drawable.produktinfo_wasser));
        healthyProducts.add(new Product("Coca Cola Light 0.4L", 1.99, 1.0, Category.healthy, R.drawable.product_coca_cola_light_product_preview, R.drawable.produktinfo_cola_light_04));
        healthyProducts.add(new Product("Apfelsaftschorle 0.4L", 1.99, 100.0, Category.healthy, R.drawable.product_lift_apfelschorle_product_preview, R.drawable.produktinfo_apfel_mittel));

        unhealthyProducts = new ArrayList<>();

        // Hauptspeisen
        unhealthyProducts.add(new Product("Big Mac", 3.59, 509.0, Category.unhealthy, R.drawable.product_big_mac_product_preview, R.drawable.produktinfo_bigmac));
        unhealthyProducts.add(new Product("McVeggie TS", 4.59, 465.0, Category.unhealthy, R.drawable.product_mcveggie_ts_product_preview, R.drawable.produktinfo_veggiets));
        unhealthyProducts.add(new Product("Hamburger Royal TS", 3.99, 518.0, Category.unhealthy, R.drawable.product_hamburger_royal_ts_product_preview, R.drawable.produktinfo_royalts));

        // Beilagen
        unhealthyProducts.add(new Product("Pommes mittel", 2.39, 341.0, Category.unhealthy, R.drawable.product_pommes_frites_mittel_product_preview, R.drawable.produktinfo_pommes_mittel));
        unhealthyProducts.add(new Product("Pommes groß", 2.79, 448.0, Category.unhealthy, R.drawable.product_pommes_frites_gross_product_preview, R.drawable.produktinfo_pommes_gross));
        unhealthyProducts.add(new Product("McFlurry Schokolinsen", 2.99, 350.0, Category.unhealthy, R.drawable.product_mcflurry__smarties__product_preview, R.drawable.produktinfo_mcflurry));

        // Getränke
        unhealthyProducts.add(new Product("Coca Cola 0.4L", 1.99, 170.0, Category.unhealthy, R.drawable.product_coca_cola_product_preview, R.drawable.produktinfo_cola_04));
        unhealthyProducts.add(new Product("Coca Cola 0.5L", 2.39, 210.0, Category.unhealthy, R.drawable.product_coca_cola_product_preview, R.drawable.produktinfo_cola_05));
        unhealthyProducts.add(new Product("Sprite 0.4L", 1.99, 148.0, Category.unhealthy, R.drawable.product_sprite_product_preview, R.drawable.produktinfo_sprite_04));
        unhealthyProducts.add(new Product("Sprite 0.5L", 2.39, 185.0, Category.unhealthy, R.drawable.product_sprite_product_preview, R.drawable.produktinfo_sprite_05));
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
