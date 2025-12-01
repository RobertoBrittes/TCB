package ifpr.edu.br.controllers;

import ifpr.edu.br.model.*;
import ifpr.edu.br.model.dao.TreinoTemPlanoDAO;
import java.util.ArrayList;

public class TreinoTemPlanoController {
    public void adicionarTreinoAoPlano(TreinoTemPlano treinoTemPlano) {
        if (treinoTemPlano.getTreino() == null || treinoTemPlano.getPlano_treino() == null) {
            throw new RuntimeException("Treino ou Plano de Treino não pode ser nulo.");
        }

        TreinoTemPlanoDAO treinoTemPlanoDAO = new TreinoTemPlanoDAO();
        treinoTemPlanoDAO.adicionarTreinoAoPlano(treinoTemPlano);
    }

    public TreinoTemPlano verificarDiaDaSemana(String diaSemana, int planoId) {
        if (diaSemana == null || diaSemana.isBlank()) {
            throw new RuntimeException("Dia da semana não pode ser nulo ou vazio.");
        }

        TreinoTemPlanoDAO treinoTemPlanoDAO = new TreinoTemPlanoDAO();
        return treinoTemPlanoDAO.verificarDiaDaSemana(diaSemana, planoId);
    }

    public ArrayList<TreinoTemPlano> listarTreinosDoPlano(int planoId) {
        TreinoTemPlanoDAO treinoTemPlanoDAO = new TreinoTemPlanoDAO();
        return treinoTemPlanoDAO.listarTreinosDoPlano(planoId);
    }
}
