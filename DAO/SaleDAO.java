package DAO;

import resources.connection;
import src.models.Sale;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    // 1. Inserir venda
    public void addSale(Sale sale) {
        String sql = "INSERT INTO sales (client_id, employee_id, sale_date, total_value) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, sale.getClientID());
            stmt.setInt(2, sale.getEmployeeID());
            stmt.setTimestamp(3, sale.getSaleDate());
            stmt.setBigDecimal(4, sale.getTotalValue());
            stmt.executeUpdate();

            // Recuperar ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    sale.setSaleID(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar venda: " + e.getMessage());
        }
    }

    // 2. Listar todas as vendas
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sales.add(new Sale(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("employee_id"),
                        rs.getTimestamp("sale_date"),
                        rs.getBigDecimal("total_value")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas: " + e.getMessage());
        }
        return sales;
    }

    // 3. Listar vendas por cliente
    public List<Sale> getSalesByClient(int clientId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales WHERE client_id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sales.add(new Sale(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("employee_id"),
                        rs.getTimestamp("sale_date"),
                        rs.getBigDecimal("total_value")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vendas do cliente: " + e.getMessage());
        }
        return sales;
    }
}
