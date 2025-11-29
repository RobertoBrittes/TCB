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

    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setDataNasc(rs.getDate("data_nasc").toLocalDate());
                return pessoa;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por ID: " + e.getMessage());
        }
    }
}
