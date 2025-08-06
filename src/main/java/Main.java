import CLI.SecaoIO;

//gustavo
//alex

public class Main {
    public static void main(String[] args) {
        boolean exec = true;

        while(exec){
            try {
                exec = SecaoIO.loopComandos();
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}
