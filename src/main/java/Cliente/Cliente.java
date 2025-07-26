package Cliente;

import java.util.ArrayList;
import java.util.List;
import Entidade.Entidade;

public class Cliente extends Entidade{
    private String nome;
    private String email;
    private String cidade;
    private String rua;
    private int numero;
    private final List<String> telefones;

    public Cliente(int id, String nome, String email, String cidade, String rua, int numero) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefones = new ArrayList<>();
    }

    public Cliente(String nome, String email, String cidade, String rua, int numero) {
        super(-1);
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefones = new ArrayList<>();
    }

    public Cliente(String nome, String cidade, String rua, int numero) {
        super(-1);
        this.nome = nome;
        this.email = null;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefones = new ArrayList<>();
    }

    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Nome: " + this.nome);
        System.out.println("Email: " + this.email);
        System.out.println("Endereco: " + this.cidade + " - " + this.rua  + " - " + this.numero);

        for(int i = 0; i < this.telefones.size(); i++){
            System.out.println("Telefone " + (i+1) +" : " + this.telefones.get(i));
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void addTelefone(String telefone) {
        this.telefones.add(telefone);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(int index, String telefone) {
        this.telefones.set(index, telefone);
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

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

    public List<String> getTelefones() {
        return telefones;
    }
}
