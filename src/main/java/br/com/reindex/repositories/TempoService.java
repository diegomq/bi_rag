package br.com.reindex.repositories;

import java.util.Date;

import br.com.reindex.domain.Dim_Tempo;

public interface TempoService {

	Dim_Tempo getTempoBy(Date dataVenda);

}
