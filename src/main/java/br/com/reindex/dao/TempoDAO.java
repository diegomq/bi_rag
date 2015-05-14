package br.com.reindex.dao;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reindex.domain.Dim_Tempo;
import br.com.reindex.repositories.TempoService;
import br.com.reindex.suri.framework.dao.DaoSupport;

@Service
@Component
public class TempoDAO extends DaoSupport<Dim_Tempo, Serializable> implements TempoService{

	@Override
	@Transactional
	public Dim_Tempo getTempoBy(Date dataVenda) {
		return retrieveByProperty(Dim_Tempo.DATA, dataVenda).get(0);
	}

}
