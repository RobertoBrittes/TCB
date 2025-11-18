package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Plano_treino;

public class Plano_TreinoDAO {
    public void salvarTreinador(Plano_treino plano_treino) {
        String sqlTreino = "INSERT INTO treino (duracao_plano, qtd_treino_semanal, dist_max_treino_longo) VALUES(?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psTreino = con.prepareStatement(sqlTreino);
            psTreino.setInt(1, plano_treino.getDuracao_plano());
            psTreino.setInt(2, plano_treino.getQtd_treino_semanal());
            psTreino.setFloat(3, plano_treino.getDist_max_treino_longo());
            psTreino.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
