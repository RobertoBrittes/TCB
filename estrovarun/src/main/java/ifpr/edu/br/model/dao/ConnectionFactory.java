package ifpr.edu.br.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
                if (conexao == null) {
                    // jdbc:gdbd://ip do servidor do BD:porta/database
                    String url = "jdbc:mysql://127.0.0.1:3306/estrovaRun";
                    String user = "root";
                    String password = "saulo2009_";
                    conexao = DriverManager.getConnection(url, user, password);
                }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conexao;

    }

}