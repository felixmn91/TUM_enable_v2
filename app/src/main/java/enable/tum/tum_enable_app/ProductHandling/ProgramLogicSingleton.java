package enable.tum.tum_enable_app.ProductHandling;

import enable.tum.tum_enable_app.R;

import java.util.ArrayList;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class ProgramLogicSingleton {
    private static ProgramLogicSingleton ourInstance = new ProgramLogicSingleton();

    private ArrayList<Product> healthyProducts;
    private ArrayList<Product> unhealthyProducts;
    private ArrayList<Product> order;

    private ProgramLogicSingleton() {
        initializeArrays();
    }

    public static ProgramLogicSingleton getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(ProgramLogicSingleton ourInstance) {
        ProgramLogicSingleton.ourInstance = ourInstance;
    }

    public static ProgramLogicSingleton getInstance() {
        return ourInstance;
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public double getKcalOfOrder() {
        double entireKcal = 0;

        for (Product p : order) {
            entireKcal += p.getKcal();
        }

        return entireKcal;
    }

    public double getPriceOfOrder() {
        double entirePrice = 0;

        for (Product p : order) {
            entirePrice += p.getPrice();
        }

        return entirePrice;
    }

    public void setOrder(ArrayList<Product> order) {
        this.order = order;
    }

    private void initializeArrays() {
        order = new ArrayList<>();

        healthyProducts = new ArrayList<>();

        healthyProducts.add(new Product("Big Salad Quinoa", 5.19, 250.0, Category.healthy, R.drawable.product_big_salad_quinoa_product_preview));
        healthyProducts.add(new Product("Cheeseburger", 1.10, 400.0, Category.healthy, R.drawable.product_cheeseburger_product_preview));
        healthyProducts.add(new Product("Chicken Nuggets", 3.19, 370.0, Category.healthy, R.drawable.product_chicken_mcnuggets_product_preview));
        healthyProducts.add(new Product("Coca Cola Light", 1.49, 60.4, Category.healthy, R.drawable.product_coca_cola_light_product_preview));

        unhealthyProducts = new ArrayList<>();

        unhealthyProducts.add(new Product("Big Mac", 4.19, 673.2, Category.unhealthy, R.drawable.product_big_mac_product_preview));
        unhealthyProducts.add(new Product("Coca Cola", 1.49, 175.8, Category.unhealthy, R.drawable.product_coca_cola_product_preview));
        unhealthyProducts.add(new Product("Mc Flurry", 2.29, 390.7, Category.unhealthy, R.drawable.product_mcflurry__smarties__product_preview));
    }

    public ArrayList<Product> getUnhealthyProducts() {
        return unhealthyProducts;
    }

    public void setUnhealthyProducts(ArrayList<Product> unhealthyProducts) {
        this.unhealthyProducts = unhealthyProducts;
    }

    public ArrayList<Product> getHealthyProducts() {
        return healthyProducts;
    }

    public void setHealthyProducts(ArrayList<Product> healthyProducts) {
        this.healthyProducts = healthyProducts;
    }
}
