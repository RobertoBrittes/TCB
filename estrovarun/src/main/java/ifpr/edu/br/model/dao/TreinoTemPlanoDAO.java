package ifpr.edu.br.model.dao;

import ifpr.edu.br.model.TreinoTemPlano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ifpr.edu.br.controllers.*;


public class TreinoTemPlanoDAO {
    public void adicionarTreinoAoPlano(TreinoTemPlano treinoPlano) {
        String sql = "INSERT INTO plano_treino_tem_treino (treino_id, plano_id, dia_semana) VALUES (?, ?, ?)";
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

    public TreinoTemPlano verificarDiaDaSemana(String diaSemana, int planoId) {
        TreinoController treinoController = new TreinoController();
        PlanoTreinoController planoTreinoController = new PlanoTreinoController();
        String sql = "SELECT * FROM plano_treino_tem_treino WHERE dia_semana = ? AND plano_id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, diaSemana);
            ps.setInt(2, planoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TreinoTemPlano treinoPlano = new TreinoTemPlano();
                treinoPlano.setTreino(treinoController.buscarTreinoPorId(rs.getInt("treino_id")));
                treinoPlano.setPlano_treino(planoTreinoController.buscarPlanoTreinoPorId(rs.getInt("plano_id")));
                treinoPlano.setDiaSemana(rs.getString("dia_semana"));
                return treinoPlano;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar dia da semana: " + e.getMessage());
        }
    }

    public ArrayList<TreinoTemPlano> listarTreinosDoPlano(int planoId) {
        TreinoController treinoController = new TreinoController();
        PlanoTreinoController planoTreinoController = new PlanoTreinoController();
        String sql = "SELECT * FROM plano_treino_tem_treino WHERE plano_id = ?";
        Connection con = ConnectionFactory.getConnection();
        ArrayList<TreinoTemPlano> treinosNoPlano = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, planoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TreinoTemPlano treinoPlano = new TreinoTemPlano();
                treinoPlano.setId(rs.getInt("id"));
                treinoPlano.setTreino(treinoController.buscarTreinoPorId(rs.getInt("treino_id")));
                treinoPlano.setPlano_treino(planoTreinoController.buscarPlanoTreinoPorId(rs.getInt("plano_id")));
                treinoPlano.setDiaSemana(rs.getString("dia_semana"));
                treinosNoPlano.add(treinoPlano);
            }
            return treinosNoPlano;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar treinos do plano: " + e.getMessage());
        }
    }

    public TreinoTemPlano buscarPorId(int id) {
        TreinoTemPlano treinoPlano = new TreinoTemPlano();
        String sql = "SELECT * FROM plano_treino_tem_treino WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                treinoPlano.setId(rs.getInt("id"));
                treinoPlano.setTreino(new TreinoController().buscarTreinoPorId(rs.getInt("treino_id")));
                treinoPlano.setPlano_treino(new PlanoTreinoController().buscarPlanoTreinoPorId(rs.getInt("plano_id")));
                treinoPlano.setDiaSemana(rs.getString("dia_semana"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar treino no plano por id: " + e.getMessage());
        }
        return treinoPlano;
    }

    public void atualizarDiaSemana(int id, String novoDiaSemana) {
        String sql = "UPDATE plano_treino_tem_treino SET dia_semana = ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novoDiaSemana);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dia da semana: " + e.getMessage());
        }
    }
}
