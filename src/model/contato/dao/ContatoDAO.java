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
        String query = "UPDATE contatos set nome='%s', email='%s' where id = %d;";
        try (Statement stm = connection.createStatement()) {
            String sql = String.format(query,
                    pContato.getNome(),
                    pContato.getEmail(),
                    pContato.getId());

            stm.execute(sql);
            logger.info("Contato atualizado com sucesso.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao atualizar contato", e);
            throw e;
        }
    }

    @Override
    public ContatoVO buscarPorEmail(String pEmail) throws Exception {
        ContatoVO contato = null;
        String query = "SELECT id, nome, email from contatos where email = '%s'";

        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(String.format(query, pEmail))) {
            if(rst.next()) {
                contato = new ContatoVO();
                contato.setId(rst.getInt("id"));
                contato.setNome(rst.getString("nome"));
                contato.setEmail(rst.getString("email"));                
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao buscar conato.", e);
            throw e;
        }
        return contato;
    }

    @Override
    public List<ContatoVO> buscarTodos() throws Exception {

        List<ContatoVO> contatos = new ArrayList<>();

        String query = "SELECT id, nome, email FROM contatos;";

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
            logger.log(Level.SEVERE, "Falha ao buscar contatos.", e);
            throw e;
        }
    }

    @Override
    public void excluir(Integer pId) throws Exception{
        String query = "DELETE FROM contatos WHERE id = "+pId;

        try (Statement stm = connection.createStatement()) {
            stm.execute(query);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao excluir contato.", e);
            throw e;
        }
    }

}
