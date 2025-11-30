package ifpr.edu.br.controllers;

import ifpr.edu.br.model.dao.TreinoDAO;
import ifpr.edu.br.model.Treino;
import java.util.ArrayList;

public class TreinoController {
    public Treino buscarTreinoPorId(int idTreino) {
        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.buscarTreinoPorId(idTreino);
    }


    public void listarTreinosProntos() {
        TreinoDAO treinoDAO = new TreinoDAO();
        ArrayList<Treino> treinosProntos = treinoDAO.listarTreinosProntos();
        System.out.println("=== Lista de Treinos Prontos ===");
        for (Treino treino : treinosProntos) {
            System.out.println("ID: " + treino.getIdTreino() + ", Nome: " + treino.getNome());
            System.out.println("Tipo de Treino: " + treino.getTipo_treino());
            System.out.println("Descrição: " + treino.getDescricao());
            System.out.println();
        }
    }

    public Treino salvarTreino(Treino treino) {
        if (treino == null) {
            throw new RuntimeException("Treino não pode ser nulo.");
        }

        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.salvarTreino(treino);
        return treino;
    }
}
