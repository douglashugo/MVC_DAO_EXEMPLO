package test;

import java.sql.Connection;
import java.util.List;
import model.contato.ContatoVO;
import model.contato.dao.ConexaoFactory;
import model.contato.dao.ContatoDAO;
import services.Service;

public class Playground {
    public static void main(String[] args) {
        try {
            // Cria a conexão com o banco de dados
            Connection connection = ConexaoFactory.getConexao();

            // Cria o DAO de Contato
            ContatoDAO contatoDAO = new ContatoDAO(connection);

            // Cria a camada de serviço
            Service service = new Service(contatoDAO);

            // Cria um objeto de ContatoVO para teste
            ContatoVO contato = new ContatoVO();
            contato.setNome("Teste");
            contato.setEmail("teste@example.com");

            // Salva o contato
            service.salvar(contato);

            // Busca o contato por email
            ContatoVO contatoBuscado = service.buscarPorEmail("teste@example.com");
            System.out.println("Contato buscado por email: " + contatoBuscado);

            // Atualiza o contato
            if (contatoBuscado != null) {
                contatoBuscado.setNome("Novo Nome");
                service.atualizar(contatoBuscado);
            }

            // Busca todos os contatos
            List<ContatoVO> contatos = service.buscarTodos();
            System.out.println("Lista de contatos:");
            for (ContatoVO c : contatos) {
                System.out.println(c);
            }

            // Exclui o contato pelo ID
            if (contatoBuscado != null) {
                service.excluir(contatoBuscado.getId());
            }

            // Fecha a conexão com o banco de dados
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
