package enable.tum.tum_enable_app.ProductHandling;

/**
 * Created by Lennart Mittag on 05.12.2015.
 */
public class Product {
    private String name;
    private double price;
    private double kcal;
    private Category category;
    private int ppathPicture;
    private int uniqueIdentifier;
    private int ipathPicture;

    public Product(String pName, double pPrice, double pKcal, Category pCategory, int pPathPicture, int iInfoPicture) {
        this.name = pName;
        this.price = pPrice;
        this.kcal = pKcal;
        this.category = pCategory;
        this.ppathPicture = pPathPicture;
        this.ipathPicture =iInfoPicture;

    }

    public int getpPathPicture() {
        return ppathPicture;
    }

    public void setpPathPicture(int pathPicture) {
        this.ppathPicture = pathPicture;
    }

    public int getIpathPicture() {
        return ipathPicture;
    }

    public void setIpathPicture(int ipathPicture){ this.ipathPicture=ipathPicture; }


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
