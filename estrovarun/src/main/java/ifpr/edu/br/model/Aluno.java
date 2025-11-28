package ifpr.edu.br.model;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    private Treinador treinador;

    public Aluno() {
    }

    public Aluno(String nome, String telefone, LocalDate data_nasc, Treinador treinador) {
        super(nome, telefone, data_nasc);
        this.treinador = treinador;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }
}
