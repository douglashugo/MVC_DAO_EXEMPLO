package model.contato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.contato.ContatoVO;

public class ContatoDAO implements IContato {
    private final Connection connection;
    private final Logger logger = Logger.getLogger(ContatoDAO.class.getName());

    public ContatoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(ContatoVO pContato) throws Exception {
        String query = "INSERT INTO contatos (nome, email) VALUES (?, ?);";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, pContato.getNome());
            stm.setString(2, pContato.getEmail());
            stm.execute();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao inserir contato", e);
            throw e;
        }
    }

    @Override
    public void atualizar(ContatoVO pContato) throws Exception {
        String query = "UPDATE contatos SET nome = ?, email = ? WHERE id = ?;";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, pContato.getNome());
            stm.setString(2, pContato.getEmail());
            stm.setInt(3, pContato.getId());
            stm.execute();
            logger.info("Contato atualizado com sucesso.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao atualizar contato", e);
            throw e;
        }
    }

    @Override
    public ContatoVO buscarPorEmail(String pEmail) throws Exception {
        ContatoVO contato = null;
        String query = "SELECT id, nome, email FROM contatos WHERE email = ?";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, pEmail);
            try (ResultSet rst = stm.executeQuery()) {
                if (rst.next()) {
                    contato = new ContatoVO();
                    contato.setId(rst.getInt("id"));
                    contato.setNome(rst.getString("nome"));
                    contato.setEmail(rst.getString("email"));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao buscar contato", e);
            throw e;
        }
        return contato;
    }

    @Override
    public List<ContatoVO> buscarTodos() throws Exception {
        List<ContatoVO> contatos = new ArrayList<>();
        String query = "SELECT id, nome, email FROM contatos;";

        try (PreparedStatement stm = connection.prepareStatement(query);
                ResultSet rst = stm.executeQuery()) {
            while (rst.next()) {
                ContatoVO contatoVO = new ContatoVO();
                contatoVO.setId(rst.getInt("id"));
                contatoVO.setNome(rst.getString("nome"));
                contatoVO.setEmail(rst.getString("email"));
                contatos.add(contatoVO);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao buscar contatos", e);
            throw e;
        }
        return contatos;
    }

    @Override
    public void excluir(Integer id) throws Exception {
        String query = "DELETE FROM contatos WHERE id = ?;";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            stm.execute();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao excluir contato", e);
            throw e;
        }
    }
}
