package Pedido;

import Cliente.Cliente;
import Cliente.ClienteDAO;
import DAO.DAO;
import DAO.inDAO;
import Entidade.Entidade;
import Produto.Produto;
import Produto.ProdutoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements inDAO {
    private final Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Entidade e) {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        Pedido p1 = (Pedido)e;
        PreparedStatement stmt1, stmt2, stmt3;
        int linhas = 0;

        String sql1 = "INSERT INTO tbPedido (CLI_CODIGO, PED_VALOR_TOTAL) VALUES (?, ?)";
        String sql2 = "INSERT INTO tbPedidoProduto (PED_CODIGO, PRO_CODIGO, PEP_QTD) VALUES (?, ?, ?)";
        String sql3 = "UPDATE tbProduto SET PRO_ESTOQUE -= ? WHERE PRO_CODIGO = ?";

        try {
            stmt1 = this.connection.prepareStatement(sql1);

            stmt1.setInt(1, p1.getCliente().getId());
            stmt1.setInt(2, p1.getValorTotal());
            linhas = stmt1.executeUpdate();

            sql1 = "SELECT MAX(PED_CODIGO) FROM tbPedido";
            stmt1 = this.connection.prepareStatement(sql1);

            ResultSet rs = stmt1.executeQuery();
            rs.next();
            int pedID = rs.getInt(1);

            stmt2 = this.connection.prepareStatement(sql2);
            stmt3 = this.connection.prepareStatement(sql3);

            List<Produto> produtos = p1.getProdutos();

            for(Produto produto : produtos) {
                stmt2.setInt(1, pedID);
                stmt2.setInt(2, produto.getId());
                stmt2.setInt(3, produto.getQuantidade());
                stmt2.executeUpdate();

                stmt3.setInt(1, produto.getQuantidade());
                stmt3.setInt(2, produto.getId());
                stmt3.executeUpdate();

            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Problema ao inserir novo pedido");
        }

    }

    @Override
    public Entidade buscar(int id) {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        Pedido p1 = null;
        PreparedStatement stmt1;
        PreparedStatement stmt2;
        ResultSet rs;
        String sql1 = "SELECT * FROM tbPedido WHERE PED_CODIGO = ?";
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);

        try {
            stmt1 = this.connection.prepareStatement(sql1);
            stmt1.setInt(1, id);

            rs = stmt1.executeQuery();
            while (rs.next()) {
                p1 = new Pedido(rs.getInt(1), (Cliente)clienteDAO.buscar(rs.getInt(2)), rs.getString(3));

                String sql2 = "SELECT * FROM tbPedidoProduto WHERE PED_CODIGO = ?";
                stmt2 = this.connection.prepareStatement(sql2);
                stmt2.setInt(1, rs.getInt(1));

                rs = stmt2.executeQuery();
                while (rs.next()) {
                    Produto produtoHold = (Produto)produtoDAO.buscar(rs.getInt(2));

                    p1.adcionarProduto(produtoHold,rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao buscar tabela ");
        }

        return p1;
    }

    @Override
    public List<Pedido> buscarTodos() {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        List<Pedido> lista = new ArrayList<>();
        Pedido p1;
        PreparedStatement stmt1;
        PreparedStatement stmt2;
        ResultSet rs;
        String sql1 = "SELECT * FROM tbPedido";

        try {
            stmt1 = this.connection.prepareStatement(sql1);

            rs = stmt1.executeQuery();
            while (rs.next()) {
                p1 = (Pedido) this.buscar(rs.getInt(1));

                lista.add(p1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao buscar tabela ");
        }

        return lista;
    }

    @Override
    public void atualizar(Entidade scr, Entidade dst) {

    }

    @Override
    public void remover(Entidade e) {

    }
}
