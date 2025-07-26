package Entidade;

public abstract class Entidade {
    protected int id;

    public Entidade() {
        this.id = -1;
    }

    public Entidade(int id) {
        this.id = id;
    }

    protected void exibir(){
        System.out.println("ID: " + this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
