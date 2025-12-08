package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import ifpr.edu.br.model.TreinoConcluido;

public class TreinoConcluidoDAO {

    public void registrarConclusao(int alunoId, int planoId) {
        String sql = "INSERT INTO treino_concluido (aluno_id, plano_id, data_conclusao) VALUES (?, ?, NOW())";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setInt(2, planoId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar conclusão do treino: " + e.getMessage());
        }
    }

    public int contarConcluidos(int alunoId, int planoId) {
        String sql = "SELECT COUNT(*) FROM treino_concluido WHERE aluno_id = ? AND plano_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setInt(2, planoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar treinos concluídos: " + e.getMessage());
        }
    }

    public List<TreinoConcluido> listar(int alunoId, int planoId) {
        String sql = "SELECT * FROM treino_concluido WHERE aluno_id = ? AND plano_id = ? ORDER BY data_conclusao";

        List<TreinoConcluido> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setInt(2, planoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TreinoConcluido tc = new TreinoConcluido();
                tc.setId(rs.getInt("id"));
                tc.setAlunoId(rs.getInt("aluno_id"));
                tc.setPlanoId(rs.getInt("plano_id"));
                tc.setDataConclusao(rs.getTimestamp("data_conclusao").toLocalDateTime());
                lista.add(tc);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar treinos concluídos: " + e.getMessage());
        }

        return lista;
    }
}


