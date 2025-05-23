package DAO;

import javaToSqlConnection.connection;
import src.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // 1. Inserir
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, quantity, value, category) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getProductQuantity());
            stmt.setDouble(3, product.getProductValue());
            stmt.setString(4, product.getProductCategory());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    // 2. Alterar
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, quantity = ?, value = ?, category = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getProductQuantity());
            stmt.setDouble(3, product.getProductValue());
            stmt.setString(4, product.getProductCategory());
            stmt.setInt(5, product.getProductID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // 3. Pesquisar por nome
    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa: " + e.getMessage());
        }
        return products;
    }

    // 4. Remover
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage());
        }
    }

    // 5. Listar todos
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        }
        return products;
    }

    // 6. Exibir um (por ID)
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Produto não encontrado: " + e.getMessage());
        }
        return null;
    }
}