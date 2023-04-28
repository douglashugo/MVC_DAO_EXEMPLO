package model.contato.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoFactory {

    private static final Logger logger = Logger.getLogger(ConexaoFactory.class.getName());
    private static final String url = "jdbc:mysql://localhost:3306/fatec";
    private static final String user = "root";
    private static final String passwd = "segredo";

    public static Connection getConexao() throws Exception {
        try {
            return DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao criar conexão.", e);
            throw e;
        }
    }
}
