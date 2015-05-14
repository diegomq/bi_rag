package br.com.reindex.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reindex.domain.Dim_Regiao;
import br.com.reindex.repositories.RegiaoService;
import br.com.reindex.suri.framework.dao.DaoSupport;

@Service
@Component
public class RegiaoDAO extends DaoSupport<Dim_Regiao, Serializable> implements RegiaoService{

	@Override
	@Transactional
	public List<Dim_Regiao> getAll() {
		return retrieveAll();
	}

	@Override
	@Transactional
	public Dim_Regiao getBy(Long id) {
		return retrieve(id);
	}

	@Override
	@Transactional
	public Dim_Regiao salvar(Dim_Regiao orodutoASerSalvo) {
		save(orodutoASerSalvo);
		return orodutoASerSalvo;
	}

	@Override
	@Transactional
	public void atualizar(Dim_Regiao produtoASerAtualizado) {
		update(produtoASerAtualizado);
		
	}

}
