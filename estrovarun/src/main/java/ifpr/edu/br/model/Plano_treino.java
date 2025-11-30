package ifpr.edu.br.model;

import java.time.LocalDate;

public class Plano_treino {
    private int idplano_treino;
    private int duracao_plano;
    private int qtd_treino_semanal;
    private String nome;
    private String objetivo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean isAtivo;
    private Treinador treinador;
    private Aluno aluno;
    
    public Plano_treino() {
    }

    public Plano_treino(int duracao_plano, int qtd_treino_semanal, String nome, String objetivo,
            String descricao, LocalDate dataInicio, LocalDate dataFim, boolean isAtivo,
            Treinador treinador, Aluno aluno) {
        this.duracao_plano = duracao_plano;
        this.qtd_treino_semanal = qtd_treino_semanal;
        this.nome = nome;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.isAtivo = isAtivo;
        this.treinador = treinador;
        this.aluno = aluno;
    }

    public int getIdplano_treino() {
        return idplano_treino;
    }

    public void setIdplano_treino(int idplano_treino) {
        this.idplano_treino = idplano_treino;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
