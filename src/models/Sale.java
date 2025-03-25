package src.models;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sale {
    private int saleID;
    private int clientID;
    private int employeeID;
    private Timestamp saleDate;
    private BigDecimal totalValue;

    public  Sale(int saleID, int clientID, int employeeID, Timestamp saleDate, BigDecimal totalValue){
        this.saleID = saleID;
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.saleDate = saleDate;
        this.totalValue = totalValue;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleID=" + saleID +
                ", clientID=" + clientID +
                ", employeeID=" + employeeID +
                ", saleDate=" + saleDate +
                ", totalValue=" + totalValue +
                '}';
    }
}

