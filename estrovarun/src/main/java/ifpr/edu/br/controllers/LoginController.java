package ifpr.edu.br.controllers;

import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.dao.UsuarioDAO;

public class LoginController {
    public Usuario efetuarLogin(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            throw new RuntimeException("Email e senha devem ser fornecidos.");
        }

        UsuarioDAO UsuarioDAO = new UsuarioDAO();
        Usuario usuario = UsuarioDAO.fazerLogin(email, senha);

        if (usuario == null) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getPessoa().getNome().split(" ")[0] + "!");

        return usuario;
    }
}
