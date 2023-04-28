package model.contato.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.contato.ContatoVO;

public class ContatoDAO implements IContato {

    final private Connection connection;

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
            System.out.println("Erro: " + e.getLocalizedMessage());
        }

    }

    @Override
    public ContatoVO atualizar(Integer pId, ContatoVO pContato) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public ContatoVO buscarPorId(Integer pId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public List<ContatoVO> buscarTodos() {

        List<ContatoVO> contatos = new ArrayList<>();

        String query = "SELECT nome, email FROM contatos;";

        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(query)) {

            while(rst.next()) {
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

}
