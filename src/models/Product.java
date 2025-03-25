package src.models;

public class Product {
    private int productID;
    private String productName;
    private int productQuantity;
    private double productValue;
    private String productCategory;

    public Product(int productID, String productName, int productQuantity, double productValue, String productCategory) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productValue = productValue;
        this.productCategory = productCategory;
    }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public double getProductValue() { return productValue; }
    public void setProductValue(double productValue) { this.productValue = productValue; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    @Override
    public String toString() {
        return "Product [Product code=" + productID + ", Product name=" + productName + ", Quantity=" + productQuantity + ", Value=" + productValue + ", Category=" + productCategory + "]";
    }
}
