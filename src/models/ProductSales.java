package src.models;

public class ProductSales {
    private int idItens;
    private int idVenda;
    private int codigoProduto;
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;

    public ProductSales(int idItens, int idVenda, int codigoProduto, String nomeProduto, int quantidade, double precoUnitario) {
        this.idItens = idItens;
        this.idVenda = idVenda;
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = quantidade * precoUnitario;
    }

    // Getters e Setters
    public int getIdItens() {
        return idItens;
    }

    public void setIdItens(int idItens) {
        this.idItens = idItens;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.precoTotal = this.quantidade * this.precoUnitario; // Atualiza preço total ao mudar quantidade
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.precoTotal = this.quantidade * this.precoUnitario; // Atualiza preço total ao mudar preço unitário
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void exibirItem() {
        System.out.println("Produto: " + nomeProduto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preço Unitário: R$ " + precoUnitario);
        System.out.println("Preço Total: R$ " + precoTotal);
    }
}
