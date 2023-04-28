package model.contato.dao;

import java.util.List;

import model.contato.ContatoVO;

public interface IContato {
    void salvar(ContatoVO pContato) throws Exception;

    ContatoVO atualizar(Integer pId, ContatoVO pContato) throws Exception;

    ContatoVO buscarPorId(Integer pId) throws Exception;

    List<ContatoVO> buscarTodos();

}
