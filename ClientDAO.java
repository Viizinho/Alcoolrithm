package DAO;

import javaToSqlConnection.connection;
import src.models.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // 1. Inserir
    public void addClient(Client client) {
        String sql = "INSERT INTO clients (name, phone, email, flamengo_fan, one_piece_watcher, from_sousa) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getPhoneNumber());
            stmt.setString(3, client.getEmail());
            stmt.setBoolean(4, client.isFlamengoFan());
            stmt.setBoolean(5, client.isOnePieceWatcher());
            stmt.setBoolean(6, client.isFromSousa());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // 2. Alterar
    public void updateClient(Client client) {
        String sql = "UPDATE clients SET name = ?, phone = ?, email = ?, flamengo_fan = ?, one_piece_watcher = ?, from_sousa = ? WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getPhoneNumber());
            stmt.setString(3, client.getEmail());
            stmt.setBoolean(4, client.isFlamengoFan());
            stmt.setBoolean(5, client.isOnePieceWatcher());
            stmt.setBoolean(6, client.isFromSousa());
            stmt.setInt(7, client.getClientID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // 3. Pesquisar por nome
    public List<Client> searchClientsByName(String name) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                clients.add(createClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa: " + e.getMessage());
        }
        return clients;
    }

    // 4. Remover
    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    // 5. Listar todos
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(createClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
        return clients;
    }

    // 6. Exibir um (por ID)
    public Client getClientById(int id) {
        String sql = "SELECT * FROM clients WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return createClientFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cliente não encontrado: " + e.getMessage());
        }
        return null;
    }

    // 7. Calcular desconto para cliente
    public double calculateDiscount(int clientId) {
        String sql = "SELECT flamengo_fan, one_piece_watcher, from_sousa FROM clients WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean flamengoFan = rs.getBoolean("flamengo_fan");
                boolean onePieceWatcher = rs.getBoolean("one_piece_watcher");
                boolean fromSousa = rs.getBoolean("from_sousa");

                double discount = 0.0;

                // Aplicar regras de desconto
                if (flamengoFan) discount += 5.0; // 5% de desconto para torcedores do Flamengo
                if (onePieceWatcher) discount += 5.0; // 5% de desconto para quem assiste One Piece
                if (fromSousa) discount += 5.0; // 5% de desconto para quem é de Sousa

                return discount;
            }

            return 0.0; // Sem desconto se o cliente não for encontrado

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular desconto: " + e.getMessage());
        }
    }


    private Client createClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("email")
        );
        client.setFlamengoFan(rs.getBoolean("flamengo_fan"));
        client.setOnePieceWatcher(rs.getBoolean("one_piece_watcher"));
        client.setFromSousa(rs.getBoolean("from_sousa"));
        return client;
    }

}