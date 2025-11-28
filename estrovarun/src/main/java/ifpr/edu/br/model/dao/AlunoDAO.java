package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Usuario;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        int idPessoa = pessoaDAO.salvar(aluno);
        aluno.setId(idPessoa);
        
        String sqlAluno = "INSERT INTO aluno (id) VALUES(?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psAluno = con.prepareStatement(sqlAluno);
            psAluno.setInt(1, aluno.getId());
            psAluno.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public Aluno login(String email, String senha) {
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
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setDataNasc(rs.getDate("data_nasc").toLocalDate());
                aluno.getTreinador().setId(rs.getInt("treinador_id"));

                Usuario usuario = new Usuario();
            

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
