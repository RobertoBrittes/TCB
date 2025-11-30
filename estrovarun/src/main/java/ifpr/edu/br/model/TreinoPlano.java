package ifpr.edu.br.model;

public class TreinoPlano {
    private Treino treino;
    private Plano_treino plano_treino;
    private String diaSemana;

    public TreinoPlano() {
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Plano_treino getPlano_treino() {
        return plano_treino;
    }

    public void setPlano_treino(Plano_treino plano_treino) {
        this.plano_treino = plano_treino;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    
}
