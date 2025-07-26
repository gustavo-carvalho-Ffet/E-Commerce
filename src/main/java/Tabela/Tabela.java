package Tabela;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private Cabecalho cabecalho;
    private int qtdColunas;
    private int[] maxPorColunas;
    private int comprimentoLinha = 0;
    private List<TabelaDado> dados;
    private Celula[] celula;

    public Tabela(Cabecalho cabecalho, int colunas) {
        this.cabecalho = cabecalho;
        this.qtdColunas = colunas;
        this.dados = new ArrayList<>();
        this.celula = new Celula[qtdColunas];

        maxPorColunas = new int[colunas];

        for(int i = 0; i < colunas; i++){
            maxPorColunas[i] =  cabecalho.getEspacosColunas()[i]*2 + cabecalho.getAliases()[i].length();
            celula[i] = new Celula(maxPorColunas[i]);
            comprimentoLinha += maxPorColunas[i] ;
        }
        comprimentoLinha += qtdColunas + 1;
    }

    public void desenhar(){
        cabecalho.desenhar(comprimentoLinha);

        for(int i = 0; i < this.dados.size(); i++){
            desenharProduto(dados.get(i));
            desenharLinha();
        }

    }

    public void desenharProduto(TabelaDado dado){
        for(int i = 0; i < dado.getQtdLinhas(); i++){
            for(int j = 0; j < dado.getQtdColunas(); j++){
                System.out.print("|");
                try {
                    this.celula[j].setDado(dado.get(j,i));
                } catch (IndexOutOfBoundsException e) {
                    this.celula[j].setDado("");
                }
                this.celula[j].desenhar();
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public void add(TabelaDado dado){
        this.dados.add(dado);
    }

    public void desenharLinha(){
        for(int i = 0; i < comprimentoLinha; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public void resetar(){
        dados.clear();
    }

    public int getQtdColunas() {
        return qtdColunas;
    }
}
