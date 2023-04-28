import java.sql.Connection;
import java.util.List;

import model.contato.ContatoVO;
import model.contato.dao.ConexaoFactory;
import model.contato.dao.ContatoDAO;

public class App {

    public static void main(String[] args) {
        /*
         * 
         * 
         create table fatec.contatos(
	        id int primary key auto_increment,
            nome varchar(100) not null,
            email varchar(100) not null
        );
         */

        try {
            Connection connection = ConexaoFactory.getConexao();
            ContatoDAO dao = new ContatoDAO(connection);
            ContatoVO c1 = new ContatoVO("Joao Pedro", "jp@gmail.com");
            dao.salvar(c1);
            List<ContatoVO> contatos = dao.buscarTodos();
            System.out.println(contatos);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
