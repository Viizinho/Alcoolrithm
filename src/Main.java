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
        System.out.println("Welcome to the Bard Playlist Generator!");
        Menu menu = new Menu();
        menu.showMenu();
    }
}