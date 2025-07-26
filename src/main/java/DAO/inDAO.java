package DAO;

import Entidade.*;

import java.sql.SQLException;
import java.util.List;

public interface inDAO {
    void inserir(Entidade e);
    void remover(Entidade e) ;
    Entidade buscar(int id);
    void atualizar(Entidade scr, Entidade dst);
    List buscarTodos();
}
