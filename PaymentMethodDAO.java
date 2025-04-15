package DAO;

import javaToSqlConnection.connection;
import src.models.PaymentMethod;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO {

    // 1. Inserir método de pagamento
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        String sql = "INSERT INTO payment_methods (method_name, is_active) VALUES (?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, paymentMethod.getMethodName());
            stmt.setBoolean(2, paymentMethod.isActive());
            stmt.executeUpdate();

            // Recuperar ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paymentMethod.setPaymentMethodID(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar método de pagamento: " + e.getMessage());
        }
    }

    // 2. Atualizar método de pagamento
    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        String sql = "UPDATE payment_methods SET method_name = ?, is_active = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentMethod.getMethodName());
            stmt.setBoolean(2, paymentMethod.isActive());
            stmt.setInt(3, paymentMethod.getPaymentMethodID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar método de pagamento: " + e.getMessage());
        }
    }

    // 3. Remover método de pagamento
    public void deletePaymentMethod(int id) {
        String sql = "DELETE FROM payment_methods WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir método de pagamento: " + e.getMessage());
        }
    }

    // 4. Listar todos os métodos de pagamento
    public List<PaymentMethod> getAllPaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        String sql = "SELECT * FROM payment_methods";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                methods.add(new PaymentMethod(
                        rs.getInt("id"),
                        rs.getString("method_name"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar métodos de pagamento: " + e.getMessage());
        }
        return methods;
    }

    public List<PaymentMethod> getActivePaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        String sql = "SELECT * FROM payment_methods WHERE is_active = TRUE"; // Filtro corrigido

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                methods.add(new PaymentMethod(
                        rs.getInt("id"),
                        rs.getString("method_name"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar métodos ativos: " + e.getMessage());
        }
        return methods;
    }

    // 6. Buscar método de pagamento por ID
    public PaymentMethod getPaymentMethodById(int id) {
        String sql = "SELECT * FROM payment_methods WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PaymentMethod(
                        rs.getInt("id"),
                        rs.getString("method_name"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Método de pagamento não encontrado: " + e.getMessage());
        }
        return null;
    }
}