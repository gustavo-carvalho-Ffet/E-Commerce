package Util;

import DAO.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=Ecommerce;encrypt=true;trustServerCertificate=true";
    private static Connection conexao;
    private static DAO dao;
    private static boolean login = false;

    public static DAO login(String usuario, String senha) throws IllegalArgumentException, IllegalStateException {
        try {
            if(conexao != null && !conexao.isClosed()) {
                throw new IllegalStateException("Ja esta logado");
            }
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        System.out.println("Login efetuado com sucesso");
        dao = new DAO(conexao);
        login = true;

        return dao;
    }

    public static DAO getDAO(){
        return dao;
    }

    public static void logout() throws IllegalStateException {
        try {
            conexao.close();
        } catch (NullPointerException| SQLException e) {
            throw new IllegalStateException("Login antes de Logout");
        }

        System.out.println("Logout efetuado com sucesso.");

        dao = null;
        conexao = null;
        login = false;
    }

    public static boolean sair() throws IllegalStateException {
        if (isloged()) {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    public static boolean isloged(){
        return login;
    }
}
