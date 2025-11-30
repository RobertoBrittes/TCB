package ifpr.edu.br.model.dao;

import ifpr.edu.br.model.Treino;
import ifpr.edu.br.model.TreinoPlano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TreinoPlanoDAO {
    public void adicionarTreinoAoPlano(TreinoPlano treinoPlano) {
        String sql = "INSERT INTO treino_plano (treino_id, plano_treino_id, dia_semana) VALUES (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, treinoPlano.getTreino().getIdTreino());
            ps.setInt(2, treinoPlano.getPlano_treino().getIdplano_treino());
            ps.setString(3, treinoPlano.getDiaSemana());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar treino ao plano: " + e.getMessage());
        }

    }

    public TreinoPlano verificarDiaDaSemana(String diaSemana) {
        String sql = "SELECT * FROM plano_treino_tem_treino WHERE dia_semana = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, diaSemana);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TreinoPlano treinoPlano = new TreinoPlano();
                
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
