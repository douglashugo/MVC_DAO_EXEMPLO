package model.contato.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.contato.ContatoVO;

public class ContatoDAO implements IContato {

    final private Connection connection;
    final private Logger logger = Logger
            .getLogger(ContatoDAO.class.getName());

    public ContatoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(ContatoVO pContato) throws Exception {
        String query = "INSERT INTO contatos (nome, email) values('%s','%s');";

        try (Statement stm = connection.createStatement()) {
            stm.execute(String.format(query,
                    pContato.getNome(),
                    pContato.getEmail()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao inserir contato", e);
            throw e;
        }

    }

    @Override
    public void atualizar(ContatoVO pContato) throws Exception {
        String query = "UPDATE contato set nome='%s', email='%s' where id = %d;";
        try (Statement stm = connection.createStatement()) {
            String sql = String.format(query,
                    pContato.getNome(),
                    pContato.getEmail(),
                     pContato.getId());

            stm.execute(sql);
            logger.info("Contato atualizado com sucesso.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao atualizar contato", e);
        }
    }

    @Override
    public ContatoVO buscarPorEmail(String pEmail) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public List<ContatoVO> buscarTodos() {

        List<ContatoVO> contatos = new ArrayList<>();

        String query = "SELECT nome, email FROM contatos;";

        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query)) {

            while (rst.next()) {
                ContatoVO contatoVO = new ContatoVO();
                contatoVO.setId(rst.getInt("id"));
                contatoVO.setNome(rst.getString("nome"));
                contatoVO.setEmail(rst.getString("email"));

                contatos.add(contatoVO);
            }

            return contatos;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
        }

        return contatos;
    }

    @Override
    public void excluir(Integer pId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

}
