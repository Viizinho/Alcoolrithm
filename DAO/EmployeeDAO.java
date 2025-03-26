package DAO;

import javaToSqlConnection.connection;
import src.models.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // 1. Inserir
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, role, salary) VALUES (?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getEmployeeRole());
            stmt.setFloat(3, employee.getSalary());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    // 2. Alterar
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, role = ?, salary = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getEmployeeRole());
            stmt.setFloat(3, employee.getSalary());
            stmt.setInt(4, employee.getEmployeeID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // 3. Pesquisar por nome
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE name LIKE ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getFloat("salary")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa: " + e.getMessage());
        }
        return employees;
    }

    // 4. Remover
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage());
        }
    }

    // 5. Listar todos
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getFloat("salary")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        }
        return employees;
    }

    // 6. Exibir um (por ID)
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getFloat("salary")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Funcionário não encontrado: " + e.getMessage());
        }
        return null;
    }
}