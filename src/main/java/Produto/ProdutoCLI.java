package Produto;

import CLI.CLI;
import Tabela.*;
import DAO.DAO;
import Tabela.Tabela;
import Util.Util;
import java.util.List;

public class ProdutoCLI extends CLI{

    public ProdutoCLI() {
        super(null);
        this.tabela = new Tabela(gerarCabecalho(), 5);
    }

    public ProdutoCLI(DAO dao) {
        super(dao);
        this.tabela = new Tabela(gerarCabecalho(), 5);
    }

    @Override
    public void novo() throws IllegalArgumentException, IllegalStateException{
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }

        String nome, descricao;
        double preco;
        int quantidade;

        System.out.println("Digite o nome do produto: ");
        nome = CLI.getNome();

        System.out.println("De uma descricao para o produto");
        descricao = CLI.getDesc();

        System.out.println("Digite o preço do produto(R$): ");
        preco = CLI.getDoublePositivo();

        System.out.println("Digite a quatidade em estoque do produto: ");
        quantidade = CLI.getIntPositivo();

        Produto p1 = new Produto(nome, descricao, Produto.toCentavos(preco), quantidade);

        dao.produto().inserir(p1);
    }

    @Override
    public void remover() throws  IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        System.out.println("Digite o ID do produto: ");
        int id = CLI.getIntPositivo();

        dao.produto().remover(id);
    }

    @Override
    public void alterar() throws IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        int id;
        System.out.println("Digite o ID do produto: ");
        id = CLI.getIntPositivo();

        Produto p1 = (Produto)dao.produto().buscar(id);
        Produto p2 = new Produto();

        String nome, descricao, precoSTR, quantidadeSTR;

        System.out.println("Presione Enter para manter o mesmo valor");

        System.out.println("Digite o novo nome do produto: ");
        nome = CLI.getNome();
        if(!nome.isEmpty()){
            p2.setNome(nome);
        }else{
            p2.setNome(p1.getNome());
        }

        System.out.println("De uma nova descricao para o produto");
        descricao = CLI.getDesc();
        if(!descricao.isEmpty()){
            p2.setDescricao(descricao);
        }else{
            p2.setDescricao(p1.getDescricao());
        }

        System.out.println("Digite o preço do produto(R$): ");
        precoSTR = CLI.getString(10);
        if(!precoSTR.isEmpty()){
            try {
                p2.setPreco(Produto.toCentavos(Double.parseDouble(precoSTR)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Entrada inválida: não é um número double.");
            }
        }else{
            p2.setPreco(p1.getPreco());
        }

        System.out.println("Digite a nova quatidade em estoque do produto: ");
        quantidadeSTR = CLI.getString(10);
        if(!quantidadeSTR.isEmpty()){
            try {
                p2.setQuantidade(Integer.parseInt(quantidadeSTR));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Entrada inválida: não é um número double.");
            }
        }else{
            p2.setQuantidade(p1.getQuantidade());
        }

        dao.produto().atualizar(p2, p1);
    }

    @Override
    public void mostrar() throws IllegalArgumentException, IllegalStateException {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        this.tabela.resetar();
        List<Produto> lista = dao.produto().buscarTodos();

        for(Produto produto : lista){
            TabelaDado dados = new TabelaDado(this.tabela.getQtdColunas());

            dados.add(1, String.valueOf(produto.getId()));
            dados.add(2, produto.getNome());
            dados.add(3, String.format("%.2f",Produto.toReais(produto.getPreco())));
            dados.add(4, String.valueOf(produto.getQuantidade()));
            dados.add(5, produto.getDescricao());
            this.tabela.add(dados);
        }

        tabela.desenhar();
    }

    private Cabecalho gerarCabecalho(){
        String[] aliases =  new String[]{"ID", "NOME", "PRECO", "ESTOQUE", "DESCRICAO"};
        int[] espacosColunas = new int[]{2, 5, 5, 5, 5};

        return new Cabecalho(aliases, espacosColunas);
    }
}