package Pedido;

import CLI.CLI;
import Cliente.Cliente;
import DAO.DAO;
import Produto.Produto;
import Tabela.Cabecalho;
import Tabela.Tabela;
import Tabela.TabelaDado;
import Util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoCLI extends CLI {
    public PedidoCLI(DAO dao) {
        super(dao);
        this.tabela = new Tabela(gerarCabecalho(), 7);
    }

    @Override
    public void novo() throws IllegalArgumentException, IllegalStateException{
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        int idCliente, idProduto, prodQtd;
        String loopvar = "";

        System.out.print("Digite o id do cliente: ");
        idCliente = CLI.getIntPositivo();
        Cliente c = (Cliente) dao.cliente().buscar(idCliente);
        Pedido p1 = new Pedido(c);
        ArrayList<Integer> jaPedidos = new ArrayList<>();
        ArrayList<Integer> qtdjaPedidos = new ArrayList<>();

        boolean loop = true;

        while(loop) {
            System.out.println("Digite o numero da ação");
            System.out.println("1. Adcionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Valor Total");
            System.out.println("5. Finalizar Pedido");
            System.out.println("6. Cancelar");

            Scanner sc = new Scanner(System.in);

            int opcao = 0;
            try {
                opcao = sc.nextInt();
            } catch (Exception e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o id do produto a ser adicionado: ");
                    idProduto = CLI.getIntPositivo();
                    if (jaPedidos.contains(idProduto)) {
                        System.out.println("Produto ja foi pedido");
                    } else {
                        Produto p = (Produto) dao.produto().buscar(idProduto);

                        if(p.getQuantidade() > 0){
                            jaPedidos.add(idProduto);

                            System.out.print("Digite a quantidade de produto: ");
                            prodQtd = CLI.getIntPositivo();

                            if (prodQtd <= 0 || prodQtd > p.getQuantidade()) {
                                System.out.println("Quantidade invalida");
                                jaPedidos.remove(idProduto);
                            } else {
                                p1.adcionarProduto(p, prodQtd);
                                qtdjaPedidos.add(prodQtd);
                            }
                        }else{
                            System.out.println("Produto sem estoque");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("----------------------------");
                    for (int i = 0; i < jaPedidos.size(); i++) {
                        System.out.println("Produto " + (i + 1));
                        System.out.println("ID: " + jaPedidos.get(i));
                        System.out.println("Quantidade: " + qtdjaPedidos.get(i));
                        System.out.println("----------------------------");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o id do produto a ser removido: ");
                    idProduto = CLI.getIntPositivo();
                    if (jaPedidos.contains(idProduto)) {
                        int removeI = 0;
                        var prods = p1.getProdutos();
                        for (Produto produto : prods) {
                            if (produto.getId() == idProduto) {
                                removeI = p1.getProdutos().indexOf(produto);
                                p1.removerProduto(produto, produto.getQuantidade());
                            }
                        }

                        jaPedidos.remove(removeI);
                        qtdjaPedidos.remove(removeI);

                    } else {
                        System.out.println("Produto nao encontrado");
                    }
                }
                case 4 -> {
                    System.out.println("----------------------------");
                    System.out.printf("Valor Total: %.2f\n", Produto.toReais(p1.getValorTotal()));
                    System.out.println("----------------------------");
                }
                case 5 -> {
                    dao.pedido().inserir(p1);
                    System.out.println("Pedido Realizado com " + p1.getProdutos().size() + " produto(s)");
                    loop = false;
                }
                case 6 -> {
                    System.out.println("Saindo...");
                    loop = false;
                }
                default -> {
                    System.out.println("Não é uma entrada valida");
                }
            }
        }
    }

    @Override
    public void remover() {

    }

    @Override
    public void alterar() {

    }

    @Override
    public void mostrar() {
        if(!Util.isloged()){
            throw new IllegalStateException("Deve logar antes");
        }
        System.out.print("\033[31m" );
        System.out.println("Pedido:");
        System.out.print("\033[0m" );

        this.tabela.resetar();
        List<Pedido> lista = dao.pedido().buscarTodos();

        for(Pedido pedido : lista){
            TabelaDado dados = new TabelaDado(this.tabela.getQtdColunas());

            if(!pedido.getProdutos().isEmpty()){
                dados.add(1, String.valueOf(pedido.getId()));
                dados.add(2, pedido.getCliente().getNome());
                for(Produto produto : pedido.getProdutos()){
                    dados.add(3, produto.getNome());
                    dados.add(4, String.format("%.2f",Produto.toReais(produto.getPreco())));
                    dados.add(5, String.valueOf(produto.getQuantidade()));
                }
                dados.add(6, String.format("%.2f",Produto.toReais(pedido.getValorTotal())));
                dados.add(7, pedido.getData());

                this.tabela.add(dados);
            }
        }

        tabela.desenhar();
    }

    private Cabecalho gerarCabecalho(){
        String[] aliases =  new String[]{"ID", "CLIENTE", "PRODUTO", "PREÇO", "QUANTIDADE", "VALOR TOTAL", "DATA"};
        int[] espacosColunas = new int[]{3, 10, 10, 7, 7, 7, 15};

        return new Cabecalho(aliases, espacosColunas);
    }
}
