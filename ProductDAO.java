package DAO;

import javaToSqlConnection.connection;
import src.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // 1. Inserir produto com campo manufactured_in_mari
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, quantity, value, category, manufactured_in_mari) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getProductQuantity());
            stmt.setDouble(3, product.getProductValue());
            stmt.setString(4, product.getProductCategory());
            stmt.setBoolean(5, product.isManufacturedInMari());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    // 2. Alterar produto com campo manufactured_in_mari
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, quantity = ?, value = ?, category = ?, manufactured_in_mari = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getProductQuantity());
            stmt.setDouble(3, product.getProductValue());
            stmt.setString(4, product.getProductCategory());
            stmt.setBoolean(5, product.isManufacturedInMari());
            stmt.setInt(6, product.getProductID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // 3. Pesquisar produtos por nome
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
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa: " + e.getMessage());
        }
        return products;
    }

    // 4. Pesquisar produtos por faixa de preço
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE value BETWEEN ? AND ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa por faixa de preço: " + e.getMessage());
        }
        return products;
    }

    // 5. Pesquisar produtos por categoria
    public List<Product> searchProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa por categoria: " + e.getMessage());
        }
        return products;
    }

    // 6. Pesquisar produtos fabricados em Mari
    public List<Product> searchProductsManufacturedInMari() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE manufactured_in_mari = TRUE";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa por produtos de Mari: " + e.getMessage());
        }
        return products;
    }

    // 7. Listar produtos com baixo estoque (menos de 5 unidades)
    public List<Product> getLowStockProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE quantity < 5";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("value"),
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos com estoque baixo: " + e.getMessage());
        }
        return products;
    }

    // 8. Remover produto
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

    // 9. Listar todos os produtos
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
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        }
        return products;
    }

    // 10. Exibir um produto (por ID)
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
                        rs.getString("category"),
                        rs.getBoolean("manufactured_in_mari")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Produto não encontrado: " + e.getMessage());
        }
        return null;
    }

    // 11. Verificar disponibilidade de estoque
    public boolean hasSufficientStock(int productId, int quantity) {
        String sql = "SELECT quantity FROM products WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt("quantity") >= quantity;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar estoque: " + e.getMessage());
        }
    }

    // 12. Atualizar estoque após venda
    public void updateStock(int productId, int quantity) {
        String sql = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }
    //public boolean validateStock(List<ProductSales> items) {
      //  for (ProductSales item : items) {
        //    if (!hasSufficientStock(item.getProductId(), item.getQuantity())) {
          //      return false;
           // }
        //}
        //return true;
    //}
}