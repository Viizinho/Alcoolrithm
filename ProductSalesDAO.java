package DAO;

import javaToSqlConnection.connection;
import src.models.ProductSales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSalesDAO {

    // 1. Inserir item de venda
    public void addProductSale(ProductSales productSale) {
        String sql = "INSERT INTO product_sales (sale_id, product_id, quantity, item_value_unit, total_value) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productSale.getSaleId());
            stmt.setInt(2, productSale.getProductId());
            stmt.setInt(3, productSale.getQuantity());
            stmt.setBigDecimal(4, productSale.getItemValueUnit());
            stmt.setBigDecimal(5, productSale.getTotalValue());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar item de venda: " + e.getMessage());
        }
    }

    // 2. Listar itens por venda
    public List<ProductSales> getSalesItems(int saleId) {
        List<ProductSales> items = new ArrayList<>();
        String sql = "SELECT * FROM product_sales WHERE sale_id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                items.add(new ProductSales(
                        rs.getInt("item_id"),
                        rs.getInt("sale_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("item_value_unit"),
                        rs.getBigDecimal("total_value")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens da venda: " + e.getMessage());
        }
        return items;
    }

    // 3. Remover item de venda
    public void deleteProductSale(int itemId) {
        String sql = "DELETE FROM product_sales WHERE item_id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item de venda: " + e.getMessage());
        }
    }
}