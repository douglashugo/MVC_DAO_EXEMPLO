import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/fatec";
        String user = "root";
        String passwd = "segredo";

        ResultSet rst = DriverManager
                .getConnection(url, user, passwd)
                .createStatement()
                .executeQuery("select version() as versao;");

        if(rst.next()) {
            String versao = rst.getString("versao");
            System.out.printf("Versão: %s\n", versao);
        }

    }
}
