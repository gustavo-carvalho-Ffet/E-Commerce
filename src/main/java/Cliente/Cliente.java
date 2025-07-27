package Cliente;

import Entidade.Entidade;

public class Cliente extends Entidade {
    private String nome;
    private String email;
    private String cidade;
    private String rua;
    private int numero;
    private int telefone;

    public Cliente() {
        super(-1);
    }

    public Cliente(int id, String nome, String email, String cidade, String rua, int numero, int telefone) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Cliente(String nome, String email, String cidade, String rua, int numero, int telefone) {
        super(-1);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Cliente(String nome, String cidade, String rua, int numero, String email, int telefone) {
        super(-1);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Nome: " + this.nome);
        System.out.println("Email: " + this.email);
        System.out.println("Endereco: " + this.cidade + " - " + this.rua + " - " + this.numero);
        System.out.println("Telefone: " + this.telefone);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public int getTelefone() {
        return telefone;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
