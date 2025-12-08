package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.Usuario;
import ifpr.edu.br.model.Pessoa;

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

    public Aluno buscarPorId(int id) {
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscarPorId(id);

        if (pessoa == null) {
            return null;
        }
        
        Aluno aluno = new Aluno();
        aluno.setId(pessoa.getId());
        aluno.setNome(pessoa.getNome());
        aluno.setTelefone(pessoa.getTelefone());
        aluno.setDataNasc(pessoa.getDataNasc());
        
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int treinadorId = rs.getInt("treinador_id");
                if (treinadorId != 0) {
                    Treinador treinador = treinadorDAO.buscarPorId(treinadorId);
                    aluno.setTreinador(treinador);
                }
            }
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por ID: " + e.getMessage());
        }
    }

    public void atualizarTreinador(int alunoId, int treinadorId) { 
        String sql = "UPDATE aluno SET treinador_id = ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, treinadorId);
            ps.setInt(2, alunoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar treinador do aluno: " + e.getMessage());
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
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setDataNasc(rs.getDate("data_nasc").toLocalDate());

                int treinadorId = rs.getInt("treinador_id");

                if (treinadorId != 0) {
                Treinador treinador = new Treinador();
                treinador.setId(treinadorId);
                aluno.setTreinador(treinador);
                }   

                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setPessoa(aluno);
                
                return usuario;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login do aluno: " + e.getMessage());
            
        }
    }

    
}
