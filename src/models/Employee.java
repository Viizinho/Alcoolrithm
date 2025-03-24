package src.models;

public class Employee {
    private int id;
    private String nome;
    private String cargo;
    private double salario;

    public Employee(int id, String nome, String cargo, double salario) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public String toString() {
        return "Funcionário [ID=" + id + ", Nome=" + nome + ", Quantidade=" + ", Cargo=" + cargo + ", Salario=" + salario + "]";
    }
}
