package Tabela;

public class Cabecalho {
    private String[] aliases;
    private int[] espacosColunas;
    int qtdColunas;

    public Cabecalho(String[] a, int[] e) throws IllegalArgumentException{
        if(a.length != e.length){
            throw new IllegalArgumentException();
        }

        qtdColunas = a.length;
        aliases = new String[]{};
        espacosColunas = new int[]{};

        this.aliases = a;
        this.espacosColunas = e;
    }

    void desenhar(int tamLinha){
        System.out.print("\033[31m" );

        for(int i = 0; i < tamLinha; i++){
            System.out.print("-");
        }
        System.out.println();

        for(int i = 0; i < qtdColunas; i++){
            System.out.print("|");
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < aliases[i].length(); j++){
                System.out.print(" ");
            }
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
        }
        System.out.println("|");


        for(int i = 0; i < qtdColunas; i++){
            System.out.print("|");
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
            System.out.print(aliases[i] );
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
        }
        System.out.println("|");


        for(int i = 0; i < qtdColunas; i++){
            System.out.print("|");
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < aliases[i].length(); j++){
                System.out.print(" ");
            }
            for(int j = 0; j < espacosColunas[i]; j++){
                System.out.print(" ");
            }
        }
        System.out.println("|");

        for(int i = 0; i < tamLinha; i++){
            System.out.print("-");
        }
        System.out.println();
        System.out.print("\033[0m");
    }

    String[] getAliases() {
        return aliases;
    }

    int[] getEspacosColunas() {
        return espacosColunas;
    }
}