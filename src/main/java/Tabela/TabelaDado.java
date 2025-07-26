package Tabela;

import java.util.ArrayList;
import java.util.List;

public class TabelaDado {
    private List<List<String>> dados;
    private int qtdColunas;
    private int qtdLinhas = 0;

    public TabelaDado(int qtdColunas){
        this.dados = new ArrayList<>();
        this.qtdColunas = qtdColunas;

        for(int i = 0; i < qtdColunas; i++){
            dados.add(new ArrayList<>());
        }
    }

    public void add(int coluna, String dado){
        coluna--;
        dados.get(coluna).add(dado);

        if(dados.get(coluna).size() > this.qtdLinhas){
            this.qtdLinhas = dados.get(coluna).size();
        }
    }

    public String get(int coluna, int index) throws  IndexOutOfBoundsException{
        return this.dados.get(coluna).get(index);
    }

    public int getQtdColunas(){
        return this.qtdColunas;
    }

    public int getQtdLinhas(){
        return this.qtdLinhas;
    }
}