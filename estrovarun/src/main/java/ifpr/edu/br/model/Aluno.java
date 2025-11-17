package ifpr.edu.br.model;

import java.sql.Date;

public class Aluno {
    private int idAluno;
    private String nome;
    private String telefone;
    private String email;
    private Date data_nasc;
    private Plano_treino plano_treino;

    public Aluno() {
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
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

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public Plano_treino getPlano_treino() {
        return plano_treino;
    }

    public void setPlano_treino(Plano_treino plano_treino) {
        this.plano_treino = plano_treino;
    }
}
