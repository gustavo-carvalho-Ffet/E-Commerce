package CLI;

import Cliente.*;
import DAO.*;
import Produto.*;

import Util.Util;
import java.util.Scanner;

public class SecaoIO {
    private static final Scanner sc = new Scanner(System.in);
    private static ClienteCLI clienteCLI;
    private static ProdutoCLI produtoCLI = new ProdutoCLI();

    public static boolean loopComandos() throws IllegalStateException, IllegalArgumentException{
        System.out.print("E-Commerce : ");
        String comando = sc.nextLine();
        Opcao opcao;

        opcao = StringManip.getOpcao(comando);

        String[] comandos = comando.split("\\s+");

        switch (opcao.getTipo()) {
            case UTIL -> {
                switch (opcao.getOperacao()) {
                    case LOGIN -> {
                        DAO d = Util.login(comandos[1], comandos[2]);
                        clienteCLI = new ClienteCLI(d);
                        produtoCLI = new ProdutoCLI(d);
                    }
                    case LOGOUT ->  Util.logout();
                    case SAIR -> {
                        return Util.sair();
                    }
                }
            }
            case PRODUTO -> {
                switch (opcao.getOperacao()) {
                    case NOVO -> produtoCLI.novo();
                    case REMOVER -> produtoCLI.remover();
                    case ALTERAR -> produtoCLI.alterar();
                    case MOSTRAR -> produtoCLI.mostrar();
                }
            }
            case CLIENTE -> {
                switch (opcao.getOperacao()) {
                    //case NOVO -> clienteCLI.novo();
                    //case REMOVER -> clienteCLI.remover();
                    //case ALTERAR -> clienteCLI.alterar();
                    //case MOSTRAR -> clienteCLI.mostrar();
                }
            }
        }
        return true;
    }
}
