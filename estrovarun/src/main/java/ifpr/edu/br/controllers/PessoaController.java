package ifpr.edu.br.controllers;

import ifpr.edu.br.model.dao.PessoaDAO;

public class PessoaController {

    public void atualizarTelefone(int pessoaId, String novoTelefone) {
        if (novoTelefone.isBlank()) {
            throw new RuntimeException("O novo telefone não pode ser vazio.");
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.atualizarTelefone(pessoaId, novoTelefone);
    }

    public void atualizarNome(int pessoaId, String novoNome) {
        if (novoNome.isBlank()) {
            throw new RuntimeException("O novo nome não pode ser vazio.");
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.atualizarNome(pessoaId, novoNome);
    }
}
