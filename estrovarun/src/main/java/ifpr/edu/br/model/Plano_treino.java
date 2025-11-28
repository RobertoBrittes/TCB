package ifpr.edu.br.model;

public class Plano_treino {
    private int idplano_treino;
    private float dist_max_treino_longo;
    private int duracao_plano;
    private int qtd_treino_semanal;
    private boolean isAtivo;
    private Treinador treinador;
    private Aluno aluno;
    
    public Plano_treino() {
    }

    public int getIdplano_treino() {
        return idplano_treino;
    }

    public void setIdplano_treino(int idplano_treino) {
        this.idplano_treino = idplano_treino;
    }

    public float getDist_max_treino_longo() {
        return dist_max_treino_longo;
    }

    public void setDist_max_treino_longo(float dist_max_treino_longo) {
        this.dist_max_treino_longo = dist_max_treino_longo;
    }

    public int getDuracao_plano() {
        return duracao_plano;
    }

    public void setDuracao_plano(int duracao_plano) {
        this.duracao_plano = duracao_plano;
    }

    public int getQtd_treino_semanal() {
        return qtd_treino_semanal;
    }

    public void setQtd_treino_semanal(int qtd_treino_semanal) {
        this.qtd_treino_semanal = qtd_treino_semanal;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
