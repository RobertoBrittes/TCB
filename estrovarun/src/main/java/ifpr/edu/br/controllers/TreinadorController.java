package ifpr.edu.br.controllers;

import java.time.LocalDate;

import ifpr.edu.br.model.dao.TreinadorDAO;
import ifpr.edu.br.model.dao.UsuarioDAO;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.Usuario;

public class TreinadorController {

    public void cadastrarTreinador(String nome, String telefone, LocalDate data_nasc, String cref, String email, String senha, String tipo_usuario) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Treinador treinador = new Treinador(nome, telefone, data_nasc, cref);
        treinadorDAO.salvar(treinador);

        Usuario usuario = new Usuario(email, senha, tipo_usuario, treinador);
        usuarioDAO.salvar(usuario);
    }
}
