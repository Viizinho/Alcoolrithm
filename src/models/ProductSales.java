package src.models;


public class ProductSales {
    private int itemId; // id dos itens vendidos
    private int saleId;
    private int productId;
    private int quantity;
    private bigDecimal itemValueUnit;
    private bigDecimal totalValue;

    public ProductSales(int cartId, int saleId, int quantity, bigDecimal itemValueUnit, bigDecimal totalValue){
        this.itemId = cartId;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.itemValueUnit = itemValueUnit;
        this.totalValue = totalValue;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public bigDecimal getItemValueUnit() {
        return itemValueUnit;
    }

    public void setItemValueUnit(bigDecimal itemValueUnit) {
        this.itemValueUnit = itemValueUnit;
    }

    public bigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(bigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString(){
        return "Product sale [Item ID=" + itemId + ", Sale ID=" + saleId + ", Product ID=" + productId + " Quantity=" + quantity + " Item Value=" + itemValueUnit + " Total value=" + totalValue + "]";
    }
}
