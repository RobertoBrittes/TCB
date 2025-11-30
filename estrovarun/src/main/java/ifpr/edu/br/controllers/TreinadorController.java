package ifpr.edu.br.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ifpr.edu.br.model.dao.TreinadorDAO;
import ifpr.edu.br.model.dao.UsuarioDAO;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Pessoa;

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

    public void listarAlunos(int treinadorId) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        ArrayList<Pessoa> alunos = treinadorDAO.listarAlunos(treinadorId);
        for (Pessoa aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
        }
    }
}
