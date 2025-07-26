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

        String sql1 = "INSERT INTO tbCliente (CLI_NOME, CLI_EMAIL, CLI_CIDADE, CLI_RUA, CLI_NUMERO) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, c1.getNome());
            stmt.setString(2, c1.getEmail());
            stmt.setString(3, c1.getCidade());
            stmt.setString(4, c1.getRua());
            stmt.setInt(5, c1.getNumero());

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
        Cliente c1;
        PreparedStatement stmt;
        String sql1 = "SELECT * FROM tbCliente WHERE CLI_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            c1 = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                             rs.getString(4), rs.getString(5), rs.getInt(6)
            );

            List<String> lista = getTelefones(c1);

            for (String s : lista) {
                c1.addTelefone(s);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return c1;
    }

    public void remover(int id) {
        remover(buscar(id));
    }

    @Override
    public void remover(Entidade e) {
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
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Entidade data, Entidade dstn){
        PreparedStatement stmt;
        Cliente c1 = (Cliente)data;
        String sql1 = "UPDATE tbCliente SET CLI_NOME =  ?, CLI_EMAIL = ?, CLI_CIDADE = ?," +
                      " CLI_RUA = ?, CLI_NUMERO = ? WHERE CLI_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, c1.getNome());
            stmt.setString(2, c1.getEmail());
            stmt.setString(3, c1.getCidade());
            stmt.setString(4, c1.getRua());
            stmt.setInt(5, c1.getNumero());
            stmt.setInt(6, dstn.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("linhas alteradas com sucesso! " + linhas);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List buscarTodos() {
        return List.of();
    }

    //******************** METODOS UNICOS DA CLASSE ***************************//

    public void addTelefone(Entidade e, String telefone) {
        PreparedStatement stmt;

        String sql1 = "INSERT INTO tbClienteTelefone (CLT_TELEFONE, CLI_CODIGO) VALUES (?, ?)";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setString(1, telefone);
            stmt.setInt(2, e.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("linhas alteradas com sucesso! " + linhas);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getTelefones(Entidade e) {
        PreparedStatement stmt;
        List<String> telefones = new ArrayList<>();

        String sql1 = "SELECT * FROM tbClienteTelefone WHERE CLI_CODIGO = ?";

        try {
            stmt = this.connection.prepareStatement(sql1);

            stmt.setInt(1, e.getId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                telefones.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return telefones;
    }

    static class telefoneDAO implements inDAO {
        @Override
        public void inserir(Entidade e) {
            
        }

        @Override
        public void remover(Entidade e) {

        }

        @Override
        public Entidade buscar(int id) {
            return null;
        }

        @Override
        public List buscarTodos() {
            return List.of();
        }

        @Override
        public void atualizar(Entidade scr, Entidade dst) {

        }
    }
}
