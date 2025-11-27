package ifpr.edu.br.model;

import java.time.LocalDate;

public abstract class Pessoa {

    private String nome;
    private String telefone;
    protected String email;
    protected LocalDate dataNasc;

    public Pessoa() {}

    public Pessoa(String nome, String telefone, String email, LocalDate dataNasc) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}
