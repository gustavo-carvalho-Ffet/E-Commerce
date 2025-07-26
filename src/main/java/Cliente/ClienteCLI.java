package Cliente;

import CLI.CLI;
import DAO.DAO;

import java.sql.SQLException;

public class ClienteCLI extends CLI {
    public ClienteCLI(DAO dao) {
        super(dao);
    }

    @Override
    public void novo() throws SQLException {
        String nome, cidade, rua;
        int numero;

        try {
            System.out.println("Digite o nome do cliente: ");
            nome = CLI.getNome();

            System.out.println("Digite o cidade do cliente: ");
            cidade = CLI.getCidade();

            System.out.println("Digite a rua do cliente: ");
            rua = CLI.getRua();

            System.out.println("Digite o numero do cliente: ");
            numero = CLI.getIntPositivo();

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        Cliente c1 = new Cliente(nome, cidade, rua, numero);

        dao.produto().inserir(c1);
    }

    @Override
    public void remover() throws SQLException {
        System.out.println("Digite o ID do produto: ");
        int id = CLI.getIntPositivo();

        dao.produto().remover(id);
    }

    @Override
    public void alterar() {
        int id;

        System.out.println("Digite o ID do produto: ");
        id = CLI.getIntPositivo();


    }

    @Override
    public void mostrar() {
    }
}