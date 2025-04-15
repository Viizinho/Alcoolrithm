package src.models;

public class Product {
    private int productID;
    private String productName;
    private int productQuantity;
    private double productValue;
    private String productCategory;
    private boolean manufacturedInMari; // Novo campo

    // Constructor completo
    public Product(int productID, String productName, int productQuantity, double productValue, String productCategory, boolean manufacturedInMari) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productValue = productValue;
        this.productCategory = productCategory;
        this.manufacturedInMari = manufacturedInMari;
    }

    // Constructor sem ID (para novos produtos)
    public Product(String productName, int productQuantity, double productValue, String productCategory, boolean manufacturedInMari) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productValue = productValue;
        this.productCategory = productCategory;
        this.manufacturedInMari = manufacturedInMari;
    }

    // Constructor para compatibilidade com código existente
    public Product(int productID, String productName, int productQuantity, double productValue, String productCategory) {
        this(productID, productName, productQuantity, productValue, productCategory, false);
    }

    // Getters e Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductValue() {
        return productValue;
    }

    public void setProductValue(double productValue) {
        this.productValue = productValue;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public boolean isManufacturedInMari() {
        return manufacturedInMari;
    }

    public void setManufacturedInMari(boolean manufacturedInMari) {
        this.manufacturedInMari = manufacturedInMari;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productValue=" + productValue +
                ", productCategory='" + productCategory + '\'' +
                ", manufacturedInMari=" + manufacturedInMari +
                '}';
    }
}