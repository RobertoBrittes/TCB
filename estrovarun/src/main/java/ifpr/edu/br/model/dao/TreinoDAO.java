package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import ifpr.edu.br.model.Treino;
import java.util.ArrayList;;

public class TreinoDAO {
    public void salvarTreino(Treino treino) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO treino (nome, duracao, tipo, descricao, isTreinoPronto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, treino.getNome());
            ps.setString(2, treino.getDuracao());
            ps.setString(3, treino.getTipo_treino());
            ps.setString(4, treino.getDescricao());
            ps.setBoolean(5, treino.isTreinoPronto());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar treino: " + e.getMessage());
        }
    }

    public Treino buscarTreinoPorId(int id) {
        String sql = "SELECT * FROM treino WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Treino treino = new Treino();
                treino.setIdTreino(rs.getInt("id"));
                treino.setNome(rs.getString("nome"));
                treino.setDuracao(rs.getString("duracao"));
                treino.setTipo_treino(rs.getString("tipo"));
                treino.setDescricao(rs.getString("descricao"));
                treino.setTreinoPronto(rs.getBoolean("isTreinoPronto"));
                return treino;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar treino por ID: " + e.getMessage());
        }
    }

    public ArrayList<Treino> listarTreinosProntos() {
        String sql = "SELECT * FROM treino WHERE isTreinoPronto = TRUE";
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Treino> treinosProntos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Treino treino = new Treino();
                treino.setIdTreino(rs.getInt("id"));
                treino.setNome(rs.getString("nome"));
                treino.setDuracao(rs.getString("duracao"));
                treino.setTipo_treino(rs.getString("tipo"));
                treino.setDescricao(rs.getString("descricao"));
                treino.setTreinoPronto(rs.getBoolean("isTreinoPronto"));
                treinosProntos.add(treino);
            }
            return treinosProntos;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar treinos prontos: " + e.getMessage());
        }
    }
}
