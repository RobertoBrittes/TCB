package ifpr.edu.br.model;

import java.time.LocalDate;

public class Treinador extends Pessoa {
    private String cref;
    
    public Treinador() {
    }

    public Treinador(String nome, String telefone, LocalDate data_nasc, String cref) {
        super(nome, telefone, data_nasc);
        this.cref = cref;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }
}
