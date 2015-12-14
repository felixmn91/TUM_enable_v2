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
public class ProgramLogicSingleton implements IOrderObservable
{
    private static ProgramLogicSingleton ourInstance = new ProgramLogicSingleton();

    private List<IOrderObserver> registeredObservers;

    private ArrayList<Product> healthyProducts;
    private ArrayList<Product> unhealthyProducts;
    private ArrayList<Product> order;

    private double actualPriceOfOrder = 0d;
    private double actualKcalOfOrder = 0d;

    private ProgramLogicSingleton()
    {
        initializeArrays();
        registeredObservers = new LinkedList<>();
    }

    public static ProgramLogicSingleton getInstance()
    {
        return ourInstance;
    }

    // TODO Methode entfernen
    public ArrayList<Product> getOrder()
    {
        return order;
    }

    public double getKcalOfOrder()
    {
        return actualKcalOfOrder;
    }

    public String getKcalOfOrderAsFormattedString()
    {
        return String.format("%.2f", actualKcalOfOrder);
    }

    public double getPriceOfOrder()
    {
        return actualPriceOfOrder;
    }

    public String getPriceOfOrderAsFormattedString()
    {
        // return Math.round(actualPriceOfOrder * 100.0) / 100.0 + "";
        return String.format("%.2f", actualPriceOfOrder);
    }

    public void addProductToActualOrder(Product product)
    {
        if (order.size() < 6)
        {
            order.add(product);
        }

        updateActualOrderPrice();
        updateActualOrderKcal();

        // TODO Methodensignatur muss geändert werden zu privat.
        // TODO Methode muss aus Interface IOrderObservable entfernt werden
        informObserverChangeHasOccurred();
    }

    public void removeProductFromActualOrder(Product product)
    {
        if (order.contains(product))
        {
            order.remove(product);
        }

        updateActualOrderPrice();
        updateActualOrderKcal();

        informObserverChangeHasOccurred();
    }

    private void updateActualOrderPrice()
    {
        actualPriceOfOrder = 0d;
        for (Product p : order)
        {
            actualPriceOfOrder += p.getPrice();
        }
    }

    private void updateActualOrderKcal()
    {
        actualKcalOfOrder = 0d;
        for (Product p : order)
        {
            actualKcalOfOrder += p.getKcal();
        }
    }

    private void initializeArrays()
    {
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

    public ArrayList<Product> getUnhealthyProducts()
    {
        return unhealthyProducts;
    }

    public ArrayList<Product> getHealthyProducts()
    {
        return healthyProducts;
    }

    @Override
    public void registerAsObserver(IOrderObserver observer)
    {
        registeredObservers.add(observer);
    }

    private void informObserverChangeHasOccurred()
    {
        for (IOrderObserver orderObserver : registeredObservers)
        {
            orderObserver.onOrderChange();
        }
    }
}
