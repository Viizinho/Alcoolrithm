package src.models;

public class Employee {
    private int employeeID; //PK
    private String employeeName;
    private String employeeRole;
    private float salary;

    public Employee(int employeeID, String employeeName, String employeeRole, float salary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeRole=" + employeeRole + ", salary=" + salary + "]";
    }
}
