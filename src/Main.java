package src;

import DAO.ClientDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import DAO.SaleDAO;
import src.models.Client;
import src.models.Employee;
import src.models.Product;
import src.models.Sale;
import java.sql.Timestamp;
import java.util.List;
import src.UI.Menu;


public class Main {
    public static void main(String[] args) {
        // Cria instância do DAO
        ClientDAO clientDAO = new ClientDAO();

        // Cria novo cliente
        Client novoCliente = new Client(
                0,                   // ID (0 para auto-increment)
                "Fulano de Tal",     // Nome
                "21999998888",       // Telefone
                "fulano@email.com"   // Email
        );

        // Insere no banco
        clientDAO.addClient(novoCliente);

        System.out.println("Cliente inserido com sucesso!");
    }
}