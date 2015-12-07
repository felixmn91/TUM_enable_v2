package com.example.victoria.myapplication.com.example.victoria.ProductHandling;

import com.example.victoria.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class ProgramLogicSingleton
{
    private static ProgramLogicSingleton ourInstance = new ProgramLogicSingleton();

    private ArrayList<Product> healthyProducts;
    private ArrayList<Product> unhealthyProducts;
    private ArrayList<Product> order;

    private ProgramLogicSingleton()
    {
        initializeArrays();
    }

    public static ProgramLogicSingleton getOurInstance()
    {
        return ourInstance;
    }

    public static void setOurInstance(ProgramLogicSingleton ourInstance)
    {
        ProgramLogicSingleton.ourInstance = ourInstance;
    }

    public static ProgramLogicSingleton getInstance()
    {
        return ourInstance;
    }

    public ArrayList<Product> getOrder()
    {
        return order;
    }

    public void setOrder(ArrayList<Product> order)
    {
        this.order = order;
    }

    private void initializeArrays()
    {
        healthyProducts = new ArrayList<>();

        healthyProducts.add(new Product("Big Salad Quinoa", 5.19, 250.0, Category.healthy, R.drawable.product_bonaqa_product_preview));
        healthyProducts.add(new Product("Cheeseburger", 1.10, 400.0, Category.healthy, R.drawable.product_cheeseburger_product_preview));
        healthyProducts.add(new Product("Chicken Nuggets", 3.19, 370.0, Category.healthy, R.drawable.product_chicken_mcnuggets_product_preview));

        //Kommentar


        unhealthyProducts = new ArrayList<>();


    }

    public ArrayList<Product> getUnhealthyProducts()
    {
        return unhealthyProducts;
    }

    public void setUnhealthyProducts(ArrayList<Product> unhealthyProducts)
    {
        this.unhealthyProducts = unhealthyProducts;
    }

    public ArrayList<Product> getHealthyProducts()
    {
        return healthyProducts;
    }

    public void setHealthyProducts(ArrayList<Product> healthyProducts)
    {
        this.healthyProducts = healthyProducts;
    }
}
