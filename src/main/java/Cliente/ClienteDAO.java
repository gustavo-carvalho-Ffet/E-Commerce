package Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAO;
import DAO.inDAO;
import Entidade.*;
import Produto.Produto;
import Util.Util;

public class ClienteDAO implements inDAO {
    private final Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }


    //*************************** METODOS CRUD ********************************//
    @Override
    public void inserir(Entidade e) throws IllegalStateException, IllegalArgumentException {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }

        Cliente c1 = (Cliente)e;
        PreparedStatement stmt;

        String sql1 = "INSERT INTO tbCliente (CLI_NOME, CLI_EMAIL, CLI_CIDADE, CLI_RUA, CLI_NUMERO, CLI_TELEFONE) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, c1.getNome());
            stmt.setString(2, c1.getEmail());
            stmt.setString(3, c1.getCidade());
            stmt.setString(4, c1.getRua());
            stmt.setInt(5, c1.getNumero());
            stmt.setInt(6, c1.getTelefone());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("linhas alteradas com sucesso! "+linhas);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Entidade buscar(int id) {
        if (connection == null) {
            throw new IllegalStateException("Deve logar antes.");
        }

        Cliente c1;
        PreparedStatement stmt;
        String sql1 = "SELECT * FROM tbCliente WHERE CLI_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c1 = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6),  rs.getInt(7)
                );
            } else {
                throw new IllegalArgumentException("ID de cliente não encontrado!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar cliente: " + ex.getMessage(), ex);
        }

        return c1;
    }

    public void remover(int id) {
        remover(buscar(id));
    }

    @Override
    public void remover(Entidade e) {
        if(connection == null){
            throw new IllegalStateException("Deve logar antes.");
        }
        PreparedStatement stmt;
        String sql1 = "DELETE FROM tbCliente WHERE CLI_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setInt(1, e.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("linhas alteradas com sucesso! " + linhas);
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Problema ao remover o cliente ");
        }
    }

    @Override
    public void atualizar(Entidade data, Entidade dstn) {
        Cliente c1 = (Cliente) data;
        String sql = "UPDATE tbCliente SET CLI_NOME = ?, CLI_EMAIL = ?, CLI_CIDADE = ?, " +
                "CLI_RUA = ?, CLI_NUMERO = ?, CLI_TELEFONE = ? WHERE CLI_CODIGO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c1.getNome());
            stmt.setString(2, c1.getEmail());
            stmt.setString(3, c1.getCidade());
            stmt.setString(4, c1.getRua());
            stmt.setInt(5, c1.getNumero());
            stmt.setInt(6, c1.getTelefone());
            stmt.setInt(7, dstn.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Cliente atualizado com sucesso! " + linhas);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws IllegalStateException {
        if (connection == null) {
            throw new IllegalStateException("Deve logar antes.");
        }

        List<Cliente> lista = new ArrayList<>();
        Cliente c1;
        PreparedStatement stmt;
        ResultSet rs;
        String sql = "SELECT * FROM tbCliente";

        try {
            stmt = this.connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                c1 = new Cliente(
                        rs.getInt("CLI_CODIGO"),
                        rs.getString("CLI_NOME"),
                        rs.getString("CLI_EMAIL"),
                        rs.getString("CLI_CIDADE"),
                        rs.getString("CLI_RUA"),
                        rs.getInt("CLI_NUMERO"),
                        rs.getInt("CLI_TELEFONE")
                );
                lista.add(c1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Problema ao buscar tabela: " + e.getMessage());
        }

        return lista;
    }
}
