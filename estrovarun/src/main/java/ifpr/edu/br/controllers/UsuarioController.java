package ifpr.edu.br.controllers;

import ifpr.edu.br.model.dao.UsuarioDAO;
import ifpr.edu.br.model.Usuario;

public class UsuarioController {

    public void atualizarSenha(int usuarioId, String novaSenha) {
        if (novaSenha == null || novaSenha.isBlank()) {
            throw new RuntimeException("A nova senha não pode ser nula ou vazia.");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarSenha(usuarioId, novaSenha);
    }

    public void atualizarEmail(int usuarioId, String novoEmail) {
        if (novoEmail == null || novoEmail.isBlank()) {
            throw new RuntimeException("O novo email não pode ser nulo ou vazio.");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarEmail(usuarioId, novoEmail);
    }

    public Usuario buscarUsuarioPorId(int usuarioId) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarPorId(usuarioId);
    }

    public Usuario buscarPorPessoaId(int pessoaId) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarPorPessoaId(pessoaId);
    }
}
