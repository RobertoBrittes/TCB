package ifpr.edu.br.controllers;

import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Treinador;
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

    public Aluno atribuirTreinador(int alunoId, String emailTreinador) {
        if (emailTreinador == null || emailTreinador.isBlank()) {
            throw new RuntimeException("O email do treinador não pode ser nulo ou vazio.");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario treinador = usuarioDAO.buscarPorEmail(emailTreinador);
        if (treinador == null || !"TREINADOR".equalsIgnoreCase(treinador.getTipo_usuario())) {
            throw new RuntimeException("Treinador não encontrado com o email fornecido.");
        }
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.atualizarTreinador(alunoId, treinador.getPessoa().getId());

        //retorna o aluno ja atualizado
        return alunoDAO.buscarPorId(alunoId);
    }

    public Aluno buscarPorId(int alunoId) {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = alunoDAO.buscarPorId(alunoId);

        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado com o ID fornecido.");
        }

        return aluno;
    }

    public Treinador returnTreinador(int alunoId) {
        Aluno aluno = new AlunoController().buscarPorId(alunoId);
        return new TreinadorController().buscarPorId(aluno.getTreinador().getId());
    }
}