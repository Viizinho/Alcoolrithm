package src;

import DAO.ClientDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import DAO.SaleDAO;
import DAO.PaymentMethodDAO;
import DAO.PaymentStatusDAO;
import src.UI.Menu;
import src.models.Client;
import src.models.Employee;
import src.models.Product;
import src.models.Sale;
import src.models.PaymentMethod;
import src.models.PaymentStatus;

public class Main {
    public static void main(String[] args) {
        // Inicializa DAOs
        ClientDAO clientDAO = new ClientDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProductDAO productDAO = new ProductDAO();
        SaleDAO saleDAO = new SaleDAO();
        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
        PaymentStatusDAO paymentStatusDAO = new PaymentStatusDAO();

        // Inicia o menu principal da aplicação
        Menu.main(args);
    }
}