package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ifpr.edu.br.model.Pessoa;

public class PessoaDAO {
    public int salvar(Pessoa pessoa) {
        String sqlPessoa = "INSERT INTO pessoa (nome, telefone, data_nasc) VALUES (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psPessoa = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getTelefone());
            psPessoa.setDate(3, java.sql.Date.valueOf(pessoa.getDataNasc()));
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
