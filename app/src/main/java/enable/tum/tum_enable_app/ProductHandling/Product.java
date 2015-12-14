package enable.tum.tum_enable_app.ProductHandling;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class Product {
    private String name;
    private double price;
    private double kcal;
    private Category category;
    private int pathPicture;
    private int uniqueIdentifier;

    public Product(String pName, double pPrice, double pKcal, Category pCategory, int pPathPicture) {
        this.name = pName;
        this.price = pPrice;
        this.kcal = pKcal;
        this.category = pCategory;
        this.pathPicture = pPathPicture;
    }

    public int getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(int pathPicture) {
        this.pathPicture = pathPicture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
