package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Treinador;

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

    public Usuario fazerLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));

                if ("aluno".equalsIgnoreCase(usuario.getTipo_usuario())) {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    Aluno aluno = alunoDAO.buscarPorId(rs.getInt("pessoa_id"));
                    usuario.setPessoa(aluno);
                } else {
                    TreinadorDAO treinadorDAO = new TreinadorDAO();
                    Treinador treinador = treinadorDAO.buscarPorId(rs.getInt("pessoa_id"));
                    usuario.setPessoa(treinador);
                }
                return usuario;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage());
        }
    }

    public void atualizarSenha(int usuarioId, String novaSenha) {
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novaSenha);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    public void atualizarEmail(int usuarioId, String novoEmail) {
        String sql = "UPDATE usuario SET email = ? WHERE id =?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novoEmail);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar email: " + e.getMessage());
        }
    }

    public Usuario buscarPorId(int usuarioId) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = new Usuario();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
            }

            if (usuario.getTipo_usuario().equalsIgnoreCase("aluno")) {
                AlunoDAO alunoDAO = new AlunoDAO();
                Aluno aluno = alunoDAO.buscarPorId(rs.getInt("pessoa_id"));
                usuario.setPessoa(aluno);
                return usuario;
            } else {
                TreinadorDAO treinadorDAO = new TreinadorDAO();
                Treinador treinador = treinadorDAO.buscarPorId(rs.getInt("pessoa_id"));
                usuario.setPessoa(treinador);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario: " + e.getMessage());
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
            }

            if (usuario == null)
                return null;

            if (usuario.getTipo_usuario().equalsIgnoreCase("aluno")) {
                AlunoDAO alunoDAO = new AlunoDAO();
                Aluno aluno = alunoDAO.buscarPorId(rs.getInt("pessoa_id"));
                usuario.setPessoa(aluno);
                return usuario;
            } else {
                TreinadorDAO treinadorDAO = new TreinadorDAO();
                Treinador treinador = treinadorDAO.buscarPorId(rs.getInt("pessoa_id"));
                usuario.setPessoa(treinador);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario: " + e.getMessage());
        }
    }

    public Usuario buscarPorPessoaId(int pessoaId) {
        String sql = "SELECT * FROM usuario WHERE pessoa_id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pessoaId);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por pessoa ID: " + e.getMessage());
        }
    }
}
