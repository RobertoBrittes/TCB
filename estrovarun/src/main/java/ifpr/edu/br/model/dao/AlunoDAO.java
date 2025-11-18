package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Aluno;

public class AlunoDAO {

    public void salvarAluno(Aluno aluno) {
        String sqlAluno = "INSERT INTO aluno (nome, telefone, email, data_nasc) VALUES(?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psAluno = con.prepareStatement(sqlAluno);
            psAluno.setString(1, aluno.getNome());
            psAluno.setString(2, aluno.getTelefone());
            psAluno.setString(3, aluno.getEmail());
            psAluno.setDate(4, aluno.getData_nasc());
            psAluno.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
