package Pedido;

import Cliente.Cliente;
import Entidade.Entidade;
import Produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends Entidade {
    private Cliente cliente;
    private String data;
    private List<Produto> produtos;
    private int valorTotal = 0;

    Pedido(int id, Cliente cliente, String data) {
        super(id);
        this.cliente = cliente;
        this.data = data;
        this.produtos = new ArrayList<>();
    }

    Pedido(Cliente cliente) {
        super(-1);
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    void adcionarProduto(Produto p, int quantidade)  {
        p.setQuantidade(quantidade);
        this.produtos.add(p);

        this.valorTotal += quantidade * p.getPreco();
    }

    void removerProduto(Produto p, int quantidade)  {
        this.produtos.remove(p);

        this.valorTotal -= quantidade * p.getPreco();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }


    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getData() {
        return data;
    }
}
