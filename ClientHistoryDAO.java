package DAO;

import javaToSqlConnection.connection;
import src.models.Sale;
import src.models.ProductSales;
import src.models.Product;
import java.sql.*;
import java.util.*;

public class ClientHistoryDAO {

    private final SaleDAO saleDAO;
    private final ProductSalesDAO productSalesDAO;
    private final ProductDAO productDAO;

    public ClientHistoryDAO() {
        this.saleDAO = new SaleDAO();
        this.productSalesDAO = new ProductSalesDAO();
        this.productDAO = new ProductDAO();
    }

    /**
     * Get all sales for a specific client with detailed information
     */
    public List<Map<String, Object>> getClientOrderHistory(int clientId) {
        List<Map<String, Object>> orderHistory = new ArrayList<>();

        // First get all sales for this client
        List<Sale> clientSales = saleDAO.searchSalesByClient(clientId);

        // For each sale, get detailed information
        for (Sale sale : clientSales) {
            Map<String, Object> orderDetails = new HashMap<>();
            orderDetails.put("saleInfo", sale);

            // Get all items in this sale
            List<ProductSales> saleItems = productSalesDAO.getSalesItems(sale.getSaleID());
            List<Map<String, Object>> itemDetails = new ArrayList<>();

            // For each sale item, get the product details
            for (ProductSales item : saleItems) {
                Map<String, Object> detailedItem = new HashMap<>();
                Product product = productDAO.getProductById(item.getProductId());

                detailedItem.put("productInfo", product);
                detailedItem.put("quantity", item.getQuantity());
                detailedItem.put("unitPrice", item.getItemValueUnit());
                detailedItem.put("totalPrice", item.getTotalValue());

                itemDetails.add(detailedItem);
            }

            orderDetails.put("items", itemDetails);
            orderHistory.add(orderDetails);
        }

        return orderHistory;
    }

    /**
     * Get a specific order by sale ID with detailed information
     */
    public Map<String, Object> getOrderDetails(int saleId) {
        Map<String, Object> orderDetails = new HashMap<>();

        // Get sale information
        Sale sale = saleDAO.getSaleById(saleId);
        if (sale == null) {
            return null;
        }

        orderDetails.put("saleInfo", sale);

        // Get all items in this sale
        List<ProductSales> saleItems = productSalesDAO.getSalesItems(saleId);
        List<Map<String, Object>> itemDetails = new ArrayList<>();

        // For each sale item, get the product details
        for (ProductSales item : saleItems) {
            Map<String, Object> detailedItem = new HashMap<>();
            Product product = productDAO.getProductById(item.getProductId());

            detailedItem.put("productInfo", product);
            detailedItem.put("quantity", item.getQuantity());
            detailedItem.put("unitPrice", item.getItemValueUnit());
            detailedItem.put("totalPrice", item.getTotalValue());

            itemDetails.add(detailedItem);
        }

        orderDetails.put("items", itemDetails);
        return orderDetails;
    }

    /**
     * Get order history using the database view
     */
    public List<Map<String, Object>> getClientOrderHistoryFromView(int clientId) {
        List<Map<String, Object>> orderHistory = new ArrayList<>();
        String sql = "SELECT * FROM vw_complete_sales WHERE client_id = ? ORDER BY sale_date DESC";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> orderSummary = new HashMap<>();
                orderSummary.put("saleId", rs.getInt("sale_id"));
                orderSummary.put("saleDate", rs.getTimestamp("sale_date"));
                orderSummary.put("totalValue", rs.getBigDecimal("total_value"));
                orderSummary.put("paymentMethod", rs.getString("payment_method"));
                orderSummary.put("paymentStatus", rs.getString("payment_status"));
                orderSummary.put("employeeName", rs.getString("employee_name"));

                orderHistory.add(orderSummary);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar histórico de pedidos: " + e.getMessage());
        }

        return orderHistory;
    }

    /**
     * Calculate client discount using the stored procedure
     */
    public double calculateClientDiscountProcedure(int clientId) {
        String sql = "{CALL CalculateClientDiscount(?, ?)}";
        double discount = 0.0;

        try (Connection conn = connection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, clientId);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();

            discount = cstmt.getDouble(2);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular desconto do cliente: " + e.getMessage());
        }

        return discount;
    }
}