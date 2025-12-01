package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Plano_treino;
import ifpr.edu.br.controllers.*;

public class Plano_TreinoDAO {
    public int salvarPlano_treino(Plano_treino plano_treino) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO plano_treino (aluno_id, treinador_id, nome, objetivo, descricao, dataInicio, duracao_plano, qtd_treino_semanal, is_ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, plano_treino.getAluno().getId());
            ps.setInt(2, plano_treino.getTreinador().getId());
            ps.setString(3, plano_treino.getNome());
            ps.setString(4, plano_treino.getObjetivo());
            ps.setString(5, plano_treino.getDescricao());
            ps.setObject(6, plano_treino.getDataInicio());
            ps.setInt(7, plano_treino.getDuracao_plano());
            ps.setInt(8, plano_treino.getQtd_treino_semanal());
            ps.setBoolean(9, plano_treino.isAtivo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar plano de treino: " + e.getMessage());
        }
    }

    public void desativarPlanoTreino(int planoTreinoId) {
        String sql = "UPDATE plano_treino SET is_ativo = FALSE WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, planoTreinoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao desativar plano de treino: " + e.getMessage());
        }
    }

    public Plano_treino buscarPlanoTreinoPorId(int id) {
        AlunoController alunoController = new AlunoController();
        TreinadorController treinadorController = new TreinadorController();
        String sql = "SELECT * FROM plano_treino WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Plano_treino plano = new Plano_treino();
                plano.setIdplano_treino(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                plano.setObjetivo(rs.getString("objetivo"));
                plano.setDescricao(rs.getString("descricao"));
                plano.setDataInicio(rs.getObject("dataInicio", java.time.LocalDate.class));
                plano.setDataFim(rs.getObject("dataFim", java.time.LocalDate.class));
                plano.setDuracao_plano(rs.getInt("duracao_plano"));
                plano.setQtd_treino_semanal(rs.getInt("qtd_treino_semanal"));
                plano.setAtivo(rs.getBoolean("is_ativo"));
                plano.setAluno(alunoController.buscarPorId(rs.getInt("aluno_id")));
                plano.setTreinador(treinadorController.buscarPorId(rs.getInt("treinador_id")));
                return plano;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar plano de treino por ID: " + e.getMessage());
        }
    }
}
