package services;

import java.util.List;
import model.contato.ContatoVO;
import model.contato.dao.IContato;

public class Service {
    private IContato dao;

    public Service(IContato dao) {
        this.dao = dao;
    }

    public void salvar(ContatoVO pContato) {
        try {
            dao.salvar(pContato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(ContatoVO pContato) {
        try {
            dao.atualizar(pContato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ContatoVO buscarPorEmail(String pEmail) {
        try {
            return dao.buscarPorEmail(pEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContatoVO> buscarTodos() {
        try {
            return dao.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Integer id) {
        try {
            dao.excluir(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
