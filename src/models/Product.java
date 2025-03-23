package src.models;

public class Product {
    private int codigo;
    private String nome;
    private int quantidade;
    private double preco;
    private String categoria;

    public Product(int codigo, String nome, int quantidade, double preco, String categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Produto [Código=" + codigo + ", Nome=" + nome + ", Quantidade=" + quantidade + ", Preço=" + preco + ", Categoria=" + categoria + "]";
    }
}
