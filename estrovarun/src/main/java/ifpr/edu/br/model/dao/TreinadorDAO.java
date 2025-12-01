package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Treinador buscarPorEmail(String email) {
        String sql = "SELECT * FROM pessoa WHERE email = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                return buscarPorId(id);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar treinador por email: " + e.getMessage());
        }
    }

    public ArrayList<Pessoa> listarAlunos(int treinadorId) {
        String sql = "SELECT * FROM pessoa p JOIN aluno a ON p.id = a.id WHERE a.treinador_id = ?";
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Pessoa> alunos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, treinadorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa aluno = new Pessoa();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setDataNasc(rs.getDate("data_nasc").toLocalDate());
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos do treinador: " + e.getMessage());
        }
    }

    public void removerAluno(int alunoId) {
        String sql = "UPDATE aluno SET treinador_id = NULL WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alunoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover aluno do treinador: " + e.getMessage());
        }
    }
}