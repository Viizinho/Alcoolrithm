package src.models;
import java.math.BigDecimal;


public class ProductSales {
    private int itemId; // id dos itens vendidos
    private int saleId;
    private int productId;
    private int quantity;
    private BigDecimal itemValueUnit;
    private BigDecimal totalValue;

    public ProductSales(int cartId, int saleId, int productId ,int quantity, BigDecimal itemValueUnit, BigDecimal totalValue){
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

    public BigDecimal getItemValueUnit() {
        return itemValueUnit;
    }

    public void setItemValueUnit(BigDecimal itemValueUnit) {
        this.itemValueUnit = itemValueUnit;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString(){
        return "Product sale [Item ID=" + itemId + ", Sale ID=" + saleId + ", Product ID=" + productId + " Quantity=" + quantity + " Item Value=" + itemValueUnit + " Total value=" + totalValue + "]";
    }
}
