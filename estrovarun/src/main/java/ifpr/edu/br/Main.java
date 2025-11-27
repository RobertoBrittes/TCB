package ifpr.edu.br;

import ifpr.edu.br.model.Treinador;
import ifpr.edu.br.model.dao.PessoaDAO;

public class Main {
    public static void main(String[] args) {
        Treinador treinador = new Treinador();
        PessoaDAO pessoaDAO = new PessoaDAO();
        treinador.setNome("Roberto Brittes Gebauer");
        treinador.setTelefone("45999999999");
        treinador.setEmail("roberto@example.com");
        treinador.setData_nasc(java.sql.Date.valueOf("1980-05-15"));
        pessoaDAO.salvar(treinador);
        
    }
}
