package src.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sale {
    private int saleID;
    private int clientID;
    private int employeeID;
    private Timestamp saleDate;
    private BigDecimal totalValue;
    private int paymentMethodID;
    private int paymentStatusID;
    private BigDecimal discountPercentage;


    // Constructor de compatibilidade com código existente
    public Sale(int saleID, int clientID, int employeeID, Timestamp saleDate, BigDecimal totalValue,
                int paymentMethodID, int paymentStatusID) {
        this.saleID = saleID;
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.saleDate = saleDate;
        this.totalValue = totalValue;
        this.paymentStatusID = paymentStatusID;
        this.paymentMethodID = paymentMethodID;
        this.discountPercentage = BigDecimal.ZERO;
    }

    public void applyDiscount(double discountPercentage) {
        if (totalValue == null) {
            throw new IllegalStateException("TotalValue não pode ser nulo");
        }
        BigDecimal discount = totalValue.multiply(BigDecimal.valueOf(discountPercentage / 100));
        totalValue = totalValue.subtract(discount);
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

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public int getPaymentStatusID() {
        return paymentStatusID;
    }

    public void setPaymentStatusID(int paymentStatusID) {
        this.paymentStatusID = paymentStatusID;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleID=" + saleID +
                ", clientID=" + clientID +
                ", employeeID=" + employeeID +
                ", saleDate=" + saleDate +
                ", totalValue=" + totalValue +
                ", paymentMethodID=" + paymentMethodID +
                ", paymentStatusID=" + paymentStatusID +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}