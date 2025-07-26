package Tabela;

class Celula {
    private String dado;
    private int max;

    Celula(int max){
        this.max = max;
    }

    void setDado(String dado){
        this.dado = dado;
    }

    void desenhar() {
        if (dado == null) dado = "";

        int tamanhoDado = dado.length();

        if (tamanhoDado > max) {
            // Exibe os primeiros (max - 3) caracteres e termina com "..."
            System.out.print(dado.substring(0, max - 3) + "...");
            return;
        }

        int espacosTotais = max - tamanhoDado;
        int espacosEsquerda = espacosTotais / 2 + espacosTotais % 2; // espaço extra à esquerda se ímpar
        int espacosDireita = espacosTotais / 2;

        for (int i = 0; i < espacosEsquerda; i++) System.out.print(" ");
        System.out.print(dado);
        for (int i = 0; i < espacosDireita; i++) System.out.print(" ");
    }

}
