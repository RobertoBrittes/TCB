package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ifpr.edu.br.model.Treinador;

public class PessoaDAO {
    public int salvar(Treinador treinador) {
        String sqlPessoa = "INSERT INTO pessoa (nome, telefone, email, data_nasc) VALUES (?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psPessoa = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, treinador.getNome());
            psPessoa.setString(2, treinador.getTelefone());
            psPessoa.setString(3, treinador.getEmail());
            psPessoa.setDate(4, treinador.getData_nasc());
            psPessoa.executeUpdate();

            ResultSet rs = psPessoa.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + e.getMessage());
        }
    }
}
