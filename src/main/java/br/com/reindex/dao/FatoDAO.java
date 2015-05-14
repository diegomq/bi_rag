package br.com.reindex.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reindex.domain.FatoVendasId;
import br.com.reindex.domain.Fato_Vendas;
import br.com.reindex.repositories.FatoService;
import br.com.reindex.suri.framework.dao.DaoSupport;

@Service
@Component
public class FatoDAO extends DaoSupport<Fato_Vendas, Serializable> implements FatoService{

	@Override
	@Transactional
	public List<Fato_Vendas> getALL() {
		return retrieveAll();
	}

	@Override
	@Transactional
	public Fato_Vendas salvar(Fato_Vendas fatoVendas) {
		save(fatoVendas);
		return fatoVendas;
	}

	@Override
	@Transactional
	public Fato_Vendas getByPk(FatoVendasId id) {
		return retrieve(id);
	}

	@Override
	@Transactional
	public void atualizar(Fato_Vendas vendaAlterada) {
		update(vendaAlterada);
		
	}

}
