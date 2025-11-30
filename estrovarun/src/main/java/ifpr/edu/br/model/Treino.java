package ifpr.edu.br.model;

public class Treino {
    private int idTreino;
    private String nome;
    private String duracao;
    private String tipo_treino;
    private String descricao;
    private boolean isTreinoPronto;

    public Treino() {
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getTipo_treino() {
        return tipo_treino;
    }

    public void setTipo_treino(String tipo_treino) {
        this.tipo_treino = tipo_treino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTreinoPronto() {
        return isTreinoPronto;
    }

    public void setTreinoPronto(boolean isTreinoPronto) {
        this.isTreinoPronto = isTreinoPronto;
    }

}
