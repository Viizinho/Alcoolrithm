import java.time.LocalDateTime;
import java.util.List;
import src.models.ProductSales;

public class Sale {
    private int idVenda;
    private int idCliente;
    private int idFuncionario;
    private LocalDateTime dataVenda;
    private double valorTotal;
    private List<ProductSales> itensVenda; // Lista de produtos vendidos na venda

    public Sale(int idVenda, int idCliente, int idFuncionario, LocalDateTime dataVenda, double valorTotal) {
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ProductSales> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ProductSales> itensVenda) {
        this.itensVenda = itensVenda;
    }

    // Método para exibir detalhes da venda
    public void exibirDetalhes() {
        System.out.println("ID Venda: " + idVenda);
        System.out.println("Cliente: " + idCliente);
        System.out.println("Funcionário: " + idFuncionario);
        System.out.println("Data: " + dataVenda);
        System.out.println("Valor Total: R$ " + valorTotal);
        System.out.println("Produtos Vendidos:");
        for (ProductSales item : itensVenda) {
            System.out.println(" - " + item.getNomeProduto() + " | Qnt: " + item.getQuantidade() + " | R$ " + item.getPrecoTotal());
        }
    }
}
