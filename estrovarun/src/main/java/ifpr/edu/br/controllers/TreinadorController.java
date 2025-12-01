package ifpr.edu.br.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ifpr.edu.br.model.dao.TreinadorDAO;
import ifpr.edu.br.model.dao.UsuarioDAO;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Pessoa;
import ifpr.edu.br.model.Aluno;

public class TreinadorController {

    public static final String USER_TREINADOR = "TREINADOR";

    public void cadastrarTreinador(String nome, String telefone, LocalDate data_nasc, String cref, String email, String senha, String tipo_usuario) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Treinador treinador = new Treinador(nome, telefone, data_nasc, cref);
        treinadorDAO.salvar(treinador);

        Usuario usuario = new Usuario(email, senha, tipo_usuario, treinador);
        usuarioDAO.salvar(usuario);
    }

    public Treinador buscarPorEmail(String email) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        Treinador treinador = treinadorDAO.buscarPorEmail(email);
        if (treinador != null) {
            return treinador;
        }
        throw new RuntimeException("Treinador não encontrado com o email fornecido.");
    }

    public Treinador buscarPorId(int id) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        Treinador treinador = treinadorDAO.buscarPorId(id);
        if (treinador != null) {
            return treinador;
        }
        throw new RuntimeException("Treinador não encontrado com o ID fornecido.");
    }

    public void listarAlunos(int treinadorId) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        ArrayList<Pessoa> alunos = treinadorDAO.listarAlunos(treinadorId);

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (Pessoa aluno : alunos) {
                System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", Email: " + aluno.getTelefone());
            }
        }
    }

    public void removerAluno(int alunoId, int treinadorId) {
        Aluno aluno = new AlunoController().buscarPorId(alunoId);
        
        if (aluno == null) throw new RuntimeException("Aluno não encontrado.");
        
        if (new AlunoController().buscarPorId(alunoId).getTreinador().getId() != treinadorId) {
            throw new RuntimeException("Você não pode remover um aluno que não é seu.");
        }

        new TreinadorDAO().removerAluno(alunoId);

        System.out.println("Aluno removido com sucesso.");
    }
}
