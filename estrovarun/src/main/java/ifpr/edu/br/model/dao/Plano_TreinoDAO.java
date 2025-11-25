package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifpr.edu.br.model.Plano_treino;

public class Plano_TreinoDAO {
    public void salvarPlano_treino(Plano_treino plano_treino) {
        Connection con = ConnectionFactory.getConnection();
        String sqlTreinador = "INSERT INTO treinador (cref, nome, telefone, email, data_nasc) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement psTreinador = con.prepareStatement(sqlTreinador, Statement.RETURN_GENERATED_KEYS);
            psTreinador.setString(1, plano_treino.getTreinador().getCref());
            psTreinador.setString(2, plano_treino.getTreinador().getNome());
            psTreinador.setString(3, plano_treino.getTreinador().getTelefone());
            psTreinador.setString(4, plano_treino.getTreinador().getEmail());
            psTreinador.setDate(5, plano_treino.getTreinador().getData_nasc());

            ResultSet rs = psTreinador.getGeneratedKeys();
            int treinador_id = 0;
            if (rs.next()) treinador_id = rs.getInt(1);
            
            String sqlTreino = "INSERT INTO treino (duracao_plano, qtd_treino_semanal, dist_max_treino_longo, isAtivo) VALUES(?, ?, ?, ?)";
            
            PreparedStatement psTreino = con.prepareStatement(sqlTreino);
            psTreino.setInt(1, plano_treino.getDuracao_plano());
            psTreino.setInt(2, plano_treino.getQtd_treino_semanal());
            psTreino.setFloat(3, plano_treino.getDist_max_treino_longo());
            psTreino.setBoolean(4, plano_treino.isAtivo());
            psTreino.executeUpdate();

            if (rs.next()) 
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
