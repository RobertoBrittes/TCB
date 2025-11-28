package ifpr.edu.br.model;

import java.time.LocalDate;

public class Pessoa {

    private int id;
    private String nome;
    private String telefone;
    protected LocalDate dataNasc;

    public Pessoa() {}

    public Pessoa(String nome, String telefone, LocalDate dataNasc) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
