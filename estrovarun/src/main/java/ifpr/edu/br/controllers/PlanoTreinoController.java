package ifpr.edu.br.controllers;

import java.time.LocalDate;

import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Plano_treino;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.dao.Plano_TreinoDAO;
import ifpr.edu.br.controllers.*;

public class PlanoTreinoController {
    public Plano_treino criarPlanoTreino(Aluno aluno,Treinador treinador, String nome, String objetivo,
            String descricao, LocalDate dataInicio, int duracaoPlano, int qtdTreinoSemanal, boolean ativo) {
        
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        Plano_treino plano = new Plano_treino();

        plano.setAluno(aluno);
        plano.setTreinador(treinador);
        plano.setNome(nome);
        plano.setObjetivo(objetivo);
        plano.setDescricao(descricao);
        plano.setDataInicio(dataInicio);
        plano.setDuracao_plano(duracaoPlano);
        plano.setQtd_treino_semanal(qtdTreinoSemanal);
        plano.setAtivo(ativo);

        int planoId = plano_TreinoDAO.salvarPlano_treino(plano);
        plano.setIdplano_treino(planoId);
        return plano;
    }

    public Plano_treino buscarPlanoTreinoPorId(int id) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        return plano_TreinoDAO.buscarPlanoTreinoPorId(id);
    }
}
