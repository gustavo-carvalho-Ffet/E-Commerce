package Produto;

import DAO.inDAO;
import Entidade.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements inDAO {
    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    //*************************** METODOS CRUD ********************************//

    @Override
    public void inserir(Entidade e) throws IllegalStateException, IllegalArgumentException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        Produto p1 = (Produto)e;
        PreparedStatement stmt;
        int linhas = 0;

        String sql1 = "INSERT INTO tbProduto (PRO_NOME, PRO_DESCRICAO, PRO_PRECO, PRO_ESTOQUE) VALUES (?, ?, ?, ?)";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, p1.getNome());
            stmt.setString(2, p1.getDescricao());
            stmt.setInt(3, p1.getPreco());
            stmt.setInt(4, p1.getQuantidade());

            linhas = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Problema ao inserir novo produto");
        }

        if (linhas > 0) {
            System.out.println("linhas alteradas com sucesso! " + linhas);
        }
    }

    @Override
    public Entidade buscar(int id) throws IllegalStateException, IllegalArgumentException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        Produto p1;
        PreparedStatement stmt;
        ResultSet rs;
        String sql1 = "SELECT * FROM tbProduto WHERE PRO_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                p1 = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5)
                );
            } else {
                throw new IllegalArgumentException("ID não encontrado!");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao buscar o produto ");
        }

        return p1;
    }

    public void remover(int id) throws  IllegalStateException, IllegalArgumentException {
        remover(buscar(id));
    }

    @Override
    public void remover(Entidade e) throws  IllegalStateException, IllegalArgumentException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }
        PreparedStatement stmt;
        String sql1 = "DELETE FROM tbProduto WHERE PRO_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setInt(1, e.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("linhas alteradas com sucesso! " + linhas);
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Problema ao remover o produto ");
        }
    }

    @Override
    public void atualizar(Entidade data, Entidade dstn) throws   IllegalStateException, IllegalArgumentException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        int linhas = 0;
        PreparedStatement stmt;
        Produto p1 = (Produto)data;
        String sql1 = "UPDATE tbProduto SET PRO_NOME =  ?, PRO_DESCRICAO = ?, PRO_PRECO = ?, PRO_ESTOQUE = ? WHERE PRO_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, p1.getNome());
            stmt.setString(2, p1.getDescricao());
            stmt.setInt(3, p1.getPreco());
            stmt.setInt(4, p1.getQuantidade());
            stmt.setInt(5, dstn.getId());

            linhas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao atualizar o produto ");
        }

        if (linhas > 0) {
            System.out.println("linhas alteradas com sucesso! " + linhas);
        }
    }

    @Override
    public List<Produto> buscarTodos() throws IllegalStateException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        List<Produto> lista = new ArrayList<>();
        Produto p1;
        PreparedStatement stmt;
        ResultSet rs;
        String sql1 = "SELECT * FROM tbProduto";

        try {
            stmt = this.connection.prepareStatement(sql1);

            rs = stmt.executeQuery();
            while (rs.next()) {
                p1 = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5)
                );
                lista.add(p1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao buscar tabela ");
        }

        return lista;
    }
}
