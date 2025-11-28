package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ifpr.edu.br.model.Treino;

public class TreinoDAO {
    public void salvarTreinador(Treino treino) {
        String sqlTreino = "INSERT INTO treino (duracao, tipo_treino, descricao) VALUES(?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psTreino = con.prepareStatement(sqlTreino);
            psTreino.setString(1, treino.getDuracao());
            psTreino.setString(2, treino.getTipo_treino());
            psTreino.setString(3, treino.getDescricao());
            psTreino.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar treino: " + e.getMessage());
        }
    }
}
