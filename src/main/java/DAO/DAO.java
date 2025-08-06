package DAO;

import Cliente.*;
import Entidade.*;
import Pedido.PedidoDAO;
import Produto.*;

import java.sql.Connection;

public class DAO  {
    private final ProdutoDAO produtoDAO;
    private final ClienteDAO clienteDAO;
    private final PedidoDAO pedidoDAO;

    public DAO(Connection c) {
        this.produtoDAO = new ProdutoDAO(c);
        this.clienteDAO = new ClienteDAO(c);
        this.pedidoDAO = new PedidoDAO(c);
    }

    public ProdutoDAO produto() {
        return produtoDAO;
    }

    public ClienteDAO cliente() {
        return clienteDAO;
    }

    public PedidoDAO pedido() {
        return pedidoDAO;
    }
}
