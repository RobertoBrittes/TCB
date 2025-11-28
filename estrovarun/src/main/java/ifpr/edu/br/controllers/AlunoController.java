package ifpr.edu.br.controllers;

import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.dao.AlunoDAO;
import ifpr.edu.br.model.dao.UsuarioDAO;

import java.time.LocalDate;

import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Treinador;

public class AlunoController {

    public void cadastrarAluno(String nome, String telefone, LocalDate data_nasc, Treinador treinador, String email,
            String senha, String tipo_usuario) {
        AlunoDAO alunoDAO = new AlunoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Aluno aluno = new Aluno(nome, telefone, data_nasc, treinador);
        alunoDAO.salvar(aluno);

        Usuario usuario = new Usuario(email, senha, tipo_usuario, aluno);
        usuarioDAO.salvar(usuario);
    }
}