package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Usuario;

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
}
