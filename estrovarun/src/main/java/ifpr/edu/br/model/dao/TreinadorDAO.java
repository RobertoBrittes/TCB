package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Treinador;

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
}
