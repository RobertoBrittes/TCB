package ifpr.edu.br.controllers;

import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.dao.AlunoDAO;
import ifpr.edu.br.model.dao.UsuarioDAO;
import java.time.LocalDate;
import ifpr.edu.br.model.Aluno;


public class AlunoController {

    public static final String USER_ALUNO = "ALUNO";

    public void cadastrarAluno(String nome, String telefone, LocalDate data_nasc, String email,
            String senha, String tipo_usuario) {
        AlunoDAO alunoDAO = new AlunoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Aluno aluno = new Aluno(nome, telefone, data_nasc);
        alunoDAO.salvar(aluno);

        Usuario usuario = new Usuario(email, senha, tipo_usuario, aluno);
        usuarioDAO.salvar(usuario);
    }
}