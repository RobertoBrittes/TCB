package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.Pessoa;
import java.sql.ResultSet;

public class TreinadorDAO {
    public void salvar(Treinador treinador) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        int idPessoa = pessoaDAO.salvar(treinador);
        treinador.setId(idPessoa);

        String sqlTreinador = "INSERT INTO treinador (id, cref) VALUES(?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psTreinador = con.prepareStatement(sqlTreinador);
            psTreinador.setInt(1, treinador.getId());
            psTreinador.setString(2, treinador.getCref());
            psTreinador.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar treinador: " + e.getMessage());
        }
    }

    public Treinador buscarPorId(int id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscarPorId(id);

        if (pessoa == null) {
            return null;
        }

        Treinador treinador = new Treinador();
        treinador.setId(pessoa.getId());
        treinador.setNome(pessoa.getNome());
        treinador.setTelefone(pessoa.getTelefone());
        treinador.setDataNasc(pessoa.getDataNasc());

        String sql = "SELECT * FROM treinador WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                treinador.setCref(rs.getString("cref"));
            }
            return treinador;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar treinador por ID: " + e.getMessage());
        }
    }
}
