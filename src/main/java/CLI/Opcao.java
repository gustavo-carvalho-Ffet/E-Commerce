package CLI;

public class Opcao {
    private enOpcao operacao;
    private enOpcao tipo;

    public  Opcao() {
    }

    public void setOperacao(enOpcao operacao) {
        this.operacao = operacao;
    }

    public void setTipo(enOpcao tipo) {
        this.tipo = tipo;
    }

    public enOpcao getOperacao() {
        return operacao;
    }

    public enOpcao getTipo() {
        return tipo;
    }
}
