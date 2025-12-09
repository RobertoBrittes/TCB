package ifpr.edu.br.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Plano_treino;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.dao.Plano_TreinoDAO;

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

    public ArrayList<Plano_treino> buscarPlanoTreinoPorAlunoId(int alunoId) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        return plano_TreinoDAO.buscarPlanoTreinoPorAlunoId(alunoId);
    }
    
    public Plano_treino buscarPlanoTreinoAtivo(int alunoId) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        return plano_TreinoDAO.buscarPlanoTreinoAtivo(alunoId);
    }

    public void atualizarNome(int planoId, String novoNome) {
        if (novoNome.isBlank() || novoNome == null) {
            throw new RuntimeException("O nome não pode ser em branco.");
        }
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        plano_TreinoDAO.atualizarNome(planoId, novoNome);
    }

    public void atualizarObjetivo(int planoId, String novoObjetivo) {
        if (novoObjetivo.isBlank() || novoObjetivo == null) {
            throw new RuntimeException("O objetivo não pode ser em branco.");
        }
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        plano_TreinoDAO.atualizarObjetivo(planoId, novoObjetivo);
    }

    public void atualizarDescricao(int planoId, String novaDescricao) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        plano_TreinoDAO.atualizarDescricao(planoId, novaDescricao);
    }

    public void desativarPlanoTreino(int planoId) {
        if (planoId == 0) {
            throw new RuntimeException("Id invalido.");
        }

        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        plano_TreinoDAO.desativarPlanoTreino(planoId);
    }

    public ArrayList<Plano_treino> buscarHistoricoPlanoTreinoPorAlunoId(int alunoId) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        return plano_TreinoDAO.buscarHistoricoPlanoTreinoPorAlunoId(alunoId);
    }

    public Plano_treino verPlanoTreino(int planoId, int alunoId) {
        Plano_TreinoDAO plano_TreinoDAO = new Plano_TreinoDAO();
        Plano_treino plano = plano_TreinoDAO.buscarPlanoTreinoPorId(planoId);

        if (plano == null) {
            throw new RuntimeException("Plano de treino não encontrado.");
        }

        if (plano.getAluno().getId() != alunoId) {
            throw new RuntimeException("Acesso negado ao plano de treino.");
        }

        return plano;
    }
}
