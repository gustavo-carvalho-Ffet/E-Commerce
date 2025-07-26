package DAO;

import Cliente.*;
import Entidade.*;
import Produto.*;

import java.sql.Connection;

public class DAO  {
    private final ProdutoDAO produtoDAO;
    private final ClienteDAO clienteDAO;

    public DAO(Connection c) {
        this.produtoDAO = new ProdutoDAO(c);
        this.clienteDAO = new ClienteDAO(c);
    }

    public ProdutoDAO produto() {
        return produtoDAO;
    }

    public ClienteDAO cliente() {
        return clienteDAO;
    }
}
