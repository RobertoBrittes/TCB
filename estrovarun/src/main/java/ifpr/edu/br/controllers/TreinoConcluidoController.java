package ifpr.edu.br.controllers;

import java.util.List;
import ifpr.edu.br.model.dao.TreinoConcluidoDAO;
import ifpr.edu.br.model.dao.Plano_TreinoDAO;
import ifpr.edu.br.model.TreinoConcluido;
import ifpr.edu.br.model.Plano_treino;

public class TreinoConcluidoController {

    private TreinoConcluidoDAO treinoConcluidoDAO = new TreinoConcluidoDAO();
    private Plano_TreinoDAO planoTreinoDAO = new Plano_TreinoDAO();

    
    public void concluirTreino(int alunoId, int planoId) {
        Plano_treino plano = planoTreinoDAO.buscarPlanoTreinoPorId(planoId);

        if (plano == null || !plano.isAtivo()) {
            throw new RuntimeException("O aluno não possui um plano ativo.");
        }

        
        treinoConcluidoDAO.registrarConclusao(alunoId, planoId);

        
        int concluidos = treinoConcluidoDAO.contarConcluidos(alunoId, planoId);
        if (concluidos >= plano.getQtd_treino_semanal() * plano.getDuracao_plano()) {
            plano.setAtivo(false);
            planoTreinoDAO.desativarPlanoTreino(planoId);
        }
    }

    
    public int getTreinosConcluidos(int alunoId, int planoId) {
        return treinoConcluidoDAO.contarConcluidos(alunoId, planoId);
    }

    
    public int getTreinosTotais(Plano_treino plano) {
        return plano.getQtd_treino_semanal() * plano.getDuracao_plano();
    }

    public int getTreinosRestantes(int alunoId, Plano_treino plano) {
        int concluidos = getTreinosConcluidos(alunoId, plano.getIdplano_treino());
        return getTreinosTotais(plano) - concluidos;
    }

    public int getSemanasRestantes(int alunoId, Plano_treino plano) {
        int restantes = getTreinosRestantes(alunoId, plano);
        return (int) Math.ceil((double) restantes / plano.getQtd_treino_semanal());
    }

    public List<TreinoConcluido> listarHistorico(int alunoId, int planoId) {
        return treinoConcluidoDAO.listar(alunoId, planoId);
    }
}
