package DAO;

import javaToSqlConnection.connection;
import src.models.Sale;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    // 1. INSERIR VENDA (CREATE)
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

    // 2. ATUALIZAR VENDA (UPDATE)
    public void updateSale(Sale sale) {
        String sql = "UPDATE sales SET client_id = ?, employee_id = ?, sale_date = ?, total_value = ? WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getClientID());
            stmt.setInt(2, sale.getEmployeeID());
            stmt.setTimestamp(3, sale.getSaleDate());
            stmt.setBigDecimal(4, sale.getTotalValue());
            stmt.setInt(5, sale.getSaleID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    // 3. PESQUISAR VENDAS POR CLIENTE (SEARCH)
    public List<Sale> searchSalesByClient(int clientId) {
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

    // 4. REMOVER VENDA (DELETE)
    public void deleteSale(int saleId) {
        String sql = "DELETE FROM sales WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir venda: " + e.getMessage());
        }
    }

    // 5. LISTAR TODAS AS VENDAS (LIST ALL)
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

    // 6. EXIBIR UMA VENDA POR ID (GET ONE)
    public Sale getSaleById(int saleId) {
        String sql = "SELECT * FROM sales WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sale(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("employee_id"),
                        rs.getTimestamp("sale_date"),
                        rs.getBigDecimal("total_value")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Venda não encontrada: " + e.getMessage());
        }
        return null;
    }
}