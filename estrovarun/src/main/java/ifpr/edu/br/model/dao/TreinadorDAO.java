package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ifpr.edu.br.model.Treinador;

public class TreinadorDAO {
    public void salvarTreinador(Treinador treinador) {
        String sqlTreinador = "INSERT INTO treinador (nome, cref, telefone, email, data_nasc,) VALUES(?, ?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psTreinador = con.prepareStatement(sqlTreinador);
            psTreinador.setString(1, treinador.getNome());
            psTreinador.setString(2, treinador.getCref());
            psTreinador.setString(3, treinador.getTelefone());
            psTreinador.setString(4, treinador.getEmail());
            psTreinador.setDate(5, treinador.getData_nasc());
            psTreinador.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
