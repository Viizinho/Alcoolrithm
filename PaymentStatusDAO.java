package DAO;

import javaToSqlConnection.connection;
import src.models.PaymentStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentStatusDAO {

    // 1. Inserir status de pagamento
    public void addPaymentStatus(PaymentStatus status) {
        String sql = "INSERT INTO payment_status (status_name, description) VALUES (?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, status.getStatusName());
            stmt.setString(2, status.getDescription());
            stmt.executeUpdate();

            // Recuperar ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    status.setStatusID(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar status de pagamento: " + e.getMessage());
        }
    }

    // 2. Atualizar status de pagamento
    public void updatePaymentStatus(PaymentStatus status) {
        String sql = "UPDATE payment_status SET status_name = ?, description = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.getStatusName());
            stmt.setString(2, status.getDescription());
            stmt.setInt(3, status.getStatusID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status de pagamento: " + e.getMessage());
        }
    }

    // 3. Remover status de pagamento
    public void deletePaymentStatus(int id) {
        String sql = "DELETE FROM payment_status WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir status de pagamento: " + e.getMessage());
        }
    }

    // 4. Listar todos os status de pagamento
    public List<PaymentStatus> getAllPaymentStatus() {
        List<PaymentStatus> statusList = new ArrayList<>();
        String sql = "SELECT * FROM payment_status";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                statusList.add(new PaymentStatus(
                        rs.getInt("id"),
                        rs.getString("status_name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar status de pagamento: " + e.getMessage());
        }
        return statusList;
    }

    // 5. Buscar status de pagamento por ID
    public PaymentStatus getPaymentStatusById(int id) {
        String sql = "SELECT * FROM payment_status WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PaymentStatus(
                        rs.getInt("id"),
                        rs.getString("status_name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Status de pagamento não encontrado: " + e.getMessage());
        }
        return null;
    }
}