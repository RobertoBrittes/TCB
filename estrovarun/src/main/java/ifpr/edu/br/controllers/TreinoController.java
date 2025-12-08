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
            System.out.println("---------------------------------------------------------------");
            System.out.println();
        }
    }

    public int salvarTreino(Treino treino) {
        if (treino == null) {
            throw new RuntimeException("Treino não pode ser nulo.");
        }

        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.salvarTreino(treino);
    }

    public void atualizarNomeDoTreino(int treinoId, String novoNome) {
        if (novoNome == null || novoNome.isBlank()) {
            throw new RuntimeException("O nome do treino não pode ser nulo ou vazio.");
        }

        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.atualizarNome(treinoId, novoNome);
    }

    public void atualizarDuracao(int treinoId, String novaDuracao) {
        if (novaDuracao == null || novaDuracao.isBlank()) {
            throw new RuntimeException("A duração do treino não pode ser nula ou vazia.");
        }

        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.atualizarDuracao(treinoId, novaDuracao);
    }

    public void atualizarTipo(int treinoId, String novoTipo) {
        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.atualizarDuracao(treinoId, novoTipo);
    }

    public void atualizarDescricao(int treinoId, String novaDescricao) {
        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.atualizarDescricao(treinoId, novaDescricao);
    }
}
