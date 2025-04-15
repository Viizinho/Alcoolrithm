package src.models;

public class Client {
    private int clientID;
    private String clientName;
    private String phoneNumber;
    private String email;
    private boolean flamengoFan;
    private boolean onePieceWatcher;
    private boolean fromSousa;


    public Client(int id, String nome, String phoneNumber, String email) {
        this.clientID = id;
        this.clientName = nome;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getClientID() { return clientID; }
    public void setClientID(int clientID) { this.clientID = clientID; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isFlamengoFan() {
        return flamengoFan;
    }

    public void setFlamengoFan(boolean flamengoFan) {
        this.flamengoFan = flamengoFan;
    }

    public boolean isOnePieceWatcher() {
        return onePieceWatcher;
    }

    public void setOnePieceWatcher(boolean onePieceWatcher) {
        this.onePieceWatcher = onePieceWatcher;
    }

    public boolean isFromSousa() {
        return fromSousa;
    }

    public void setFromSousa(boolean fromSousa) {
        this.fromSousa = fromSousa;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", clientName='" + clientName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", flamengoFan=" + flamengoFan +
                ", onePieceWatcher=" + onePieceWatcher +
                ", fromSousa=" + fromSousa +
                '}';
    }
}
