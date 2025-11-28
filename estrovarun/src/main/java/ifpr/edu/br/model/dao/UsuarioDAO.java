package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Pessoa;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        String sqlUsuario = "INSERT INTO usuario (email, senha, tipo_usuario, pessoa_id) VALUES(?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psUsuario = con.prepareStatement(sqlUsuario);
            psUsuario.setString(1, usuario.getEmail());
            psUsuario.setString(2, usuario.getSenha());
            psUsuario.setString(3, usuario.getTipo_usuario());
            psUsuario.setInt(4, usuario.getPessoa().getId());
            psUsuario.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuario: " + e.getMessage());
        }
    }

    public Usuario login(String email, String senha) {
        String sqlAluno = "SELECT pessoa.id, pessoa.nome, pessoa.telefone, pessoa.data_nasc, " +
                          "aluno.treinador_id, usuario.email, usuario.senha, usuario.tipo_usuario " +
                          "FROM pessoa " +
                          "JOIN usuario ON usuario.pessoa_id = pessoa.id " +
                          "JOIN aluno ON aluno.id = pessoa.id " +
                          "WHERE usuario.email = ? AND usuario.senha = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sqlAluno);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
