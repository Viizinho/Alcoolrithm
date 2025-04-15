package src.models;

public class PaymentStatus {
    private int statusID;
    private String statusName;
    private String description;

    // Payment status types
    public static final String PENDING = "Pendente";
    public static final String CONFIRMED = "Confirmado";
    public static final String CANCELLED = "Cancelado";
    public static final String REFUNDED = "Reembolsado";
    public static final String FAILED = "Falha";

    // Constructor
    public PaymentStatus(int statusID, String statusName, String description) {
        this.statusID = statusID;
        this.statusName = statusName;
        this.description = description;
    }

    // Constructor without ID (for new status)
    public PaymentStatus(String statusName, String description) {
        this.statusName = statusName;
        this.description = description;
    }

    // Getters and Setters
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "statusID=" + statusID +
                ", statusName='" + statusName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}