package src.models;

public class PaymentMethod {
    private int paymentMethodID;
    private String methodName;
    private boolean isActive;

    // Payment method types
    public static final String CREDITO = "Cartão de Crédito";
    public static final String DEBITO = "Cartão de Débito";
    public static final String PIX = "PIX";
    public static final String BOLETO = "Boleto";
    public static final String BERRIES = "Berries";


    // Constructor
    public PaymentMethod(int paymentMethodID, String methodName, boolean isActive) {
        this.paymentMethodID = paymentMethodID;
        this.methodName = methodName;
        this.isActive = isActive;
    }

    // Constructor without ID (for new payment methods)
    public PaymentMethod(String methodName, boolean isActive) {
        this.methodName = methodName;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentMethodID=" + paymentMethodID +
                ", methodName='" + methodName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}