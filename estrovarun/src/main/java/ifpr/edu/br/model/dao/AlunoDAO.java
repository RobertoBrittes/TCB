package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Aluno;

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
}
