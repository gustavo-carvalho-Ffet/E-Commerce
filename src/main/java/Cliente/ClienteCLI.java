package Cliente;

import CLI.CLI;
import DAO.DAO;
import Tabela.Tabela;
import Tabela.TabelaDado;
import Util.Util;
import java.util.List;
import Tabela.Cabecalho;

public class ClienteCLI extends CLI {
    public ClienteCLI(DAO dao) {
        super(dao);
        this.tabela = new Tabela(gerarCabecalho(), 7);
    }

    @Override
    public void novo() throws IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }

        String nome, cidade, rua, email;
        int numero;
        String telefone;

        try {
            System.out.print("Digite o nome do cliente: ");
            nome = CLI.getNome();

            System.out.print("Digite o cidade do cliente: ");
            cidade = CLI.getCidade();

            System.out.print("Digite a rua do cliente: ");
            rua = CLI.getRua();

            System.out.print("Digite o numero do cliente: ");
            numero = CLI.getIntPositivo();

            System.out.print("Digite o email do cliente: ");
            email = CLI.getEmail();

            System.out.print("Digite o telefone do cliente: ");
            telefone = getStringExata(11);



        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        Cliente c1 = new Cliente(nome, cidade, rua, numero, email, telefone);

        dao.cliente().inserir(c1);
    }

    @Override
    public void remover() throws IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        System.out.print("Digite o ID do cliente: ");
        int id = CLI.getIntPositivo();

        dao.cliente().remover(id);
    }

    @Override
    public void alterar() throws IllegalArgumentException, IllegalStateException {
        if (!Util.isloged()) {
            throw new IllegalStateException("Deve logar antes");
        }

        System.out.print("Digite o ID do cliente: ");
        int id = CLI.getIntPositivo();

        Cliente c1 = (Cliente) dao.cliente().buscar(id);
        Cliente c2 = new Cliente();

        System.out.println("Pressione Enter para manter o mesmo valor");

        System.out.print("Digite o novo nome do cliente: ");
        String nome = CLI.getNome();
        c2.setNome(nome.isEmpty() ? c1.getNome() : nome);

        System.out.print("Digite a nova cidade do cliente: ");
        String cidade = CLI.getCidade();
        c2.setCidade(cidade.isEmpty() ? c1.getCidade() : cidade);

        System.out.print("Digite a nova rua do cliente: ");
        String rua = CLI.getRua();
        c2.setRua(rua.isEmpty() ? c1.getRua() : rua);

        System.out.print("Digite o novo número do cliente: ");
        String numeroStr = CLI.getString(10);
        if (!numeroStr.isEmpty()) {
            try {
                c2.setNumero(Integer.parseInt(numeroStr));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Número inválido.");
            }
        } else {
            c2.setNumero(c1.getNumero());
        }

        System.out.print("Digite o novo email do cliente: ");
        String email = CLI.getEmail();
        c2.setEmail(email.isEmpty() ? c1.getEmail() : email);

        System.out.print("Digite o novo telefone do cliente: ");
        String telefoneStr = CLI.getStringExata(11);
        c2.setTelefone(telefoneStr.isEmpty() ? c1.getTelefone() : telefoneStr);

        dao.cliente().atualizar(c2, c1);
    }

    @Override
    public void mostrar() throws IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        System.out.print("\033[31m" );
        System.out.println("Cliente:");
        System.out.print("\033[0m" );

        this.tabela.resetar();
        List<Cliente> lista = dao.cliente().buscarTodos();

        for(Cliente cliente : lista){
            TabelaDado dados = new TabelaDado(this.tabela.getQtdColunas());

            dados.add(1, String.valueOf(cliente.getId()));
            dados.add(2, cliente.getNome());
            dados.add(3, cliente.getEmail());
            dados.add(4, cliente.getCidade());
            dados.add(5, cliente.getRua());
            dados.add(6, String.valueOf(cliente.getNumero()));
            dados.add(7, cliente.getTelefone());

            this.tabela.add(dados);
        }

        tabela.desenhar();
    }

    private Cabecalho gerarCabecalho(){
        String[] aliases =  new String[]{"ID", "NOME", "EMAIL", "CIDADE", "RUA", "NUMERO", "TELEFONE"};
        int[] espacosColunas = new int[]{3, 10, 10, 10, 10, 7, 5};

        return new Cabecalho(aliases, espacosColunas);
    }
}