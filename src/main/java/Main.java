import CLI.SecaoIO;

public class Main {
    public static void main(String[] args) {
        int operacoes = 0;
        boolean exec = true;

        while(exec){
            try {
                exec = SecaoIO.loopComandos();
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(e.getMessage());;
            }
            operacoes++;
        }

        System.out.println("Operações feitas: " + operacoes);
    }
}
