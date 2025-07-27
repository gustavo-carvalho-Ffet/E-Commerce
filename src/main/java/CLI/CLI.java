package CLI;

import Tabela.Cabecalho;
import DAO.DAO;
import Tabela.Tabela;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class CLI {
    protected Tabela tabela;
    protected DAO dao;

    public CLI(DAO dao) {
        this.dao = dao;
    }
    public CLI(DAO dao, Tabela tabela) {
        this.dao = dao;
        this.tabela = tabela;
    }

    public abstract void novo() throws SQLException;

    public abstract void remover() throws SQLException;

    public abstract void alterar() throws SQLException;

    public abstract void mostrar() throws SQLException;

    public void setTabela(Tabela tabela){
        this.tabela = tabela;
    }
    public Tabela getTabela(){
        return this.tabela;
    }


    // metodos aux para criação de entidades

    public static String getString(int max) {
        Scanner sc = new Scanner(System.in);

        String linha = sc.nextLine();

        return linha.length() > max ? linha.substring(0, max) : linha;
    }

    public static int getIntPositivo() throws IllegalArgumentException{
        int ret;

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("Entrada inválida: não é um número inteiro.");
        }

        ret = sc.nextInt();

        if (ret < 0) {
            throw new IllegalArgumentException("Número negativo não é permitido.");
        }

        return ret;
    }

    public static double getDoublePositivo() throws IllegalArgumentException{
        double ret;

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextDouble()) {
            throw new IllegalArgumentException("Entrada inválida: não é um número double.");
        }

        ret = sc.nextDouble();

        if (ret < 0) {
            throw new IllegalArgumentException("Número negativo não é permitido.");
        }

        return ret;
    }

    public static String getNome(){
        return getString(30);
    }

    public static String getDesc(){
        return getString(50);
    }

    public static String getCidade(){
        return getString(30);
    }

    public static String getRua(){
        return getString(30);
    }

    public static String getEmail(){
        return getString(30);
    }
}
