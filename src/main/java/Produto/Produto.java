package Produto;

import Entidade.Entidade;

public class Produto extends Entidade {
    private String nome;
    private String descricao;
    private int preco;
    private int quantidade;

    public Produto(int id, String nome, String descricao, int preco, int quantidade){
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(String nome, String descricao, int preco, int quantidade){
        super(-1);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto() {
        super();
        this.nome = "";
        this.descricao = "";
        this.preco = 0;
        this.quantidade = 0;
    }

    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Nome: " + this.nome);
        System.out.println("Descricao: " + this.descricao);
        System.out.printf("Preco: %.2f\n",toReais(this.preco));
        System.out.println("Quantidade: " + this.quantidade);
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static int toCentavos(double reais){
        return Double.valueOf(reais*100).intValue();
    }

    public static double toReais(int centavos) {
        return centavos / 100.0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }
}
