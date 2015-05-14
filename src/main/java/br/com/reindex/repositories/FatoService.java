package br.com.reindex.repositories;

import java.util.List;

import br.com.reindex.domain.FatoVendasId;
import br.com.reindex.domain.Fato_Vendas;

public interface FatoService {

	public List<Fato_Vendas> getALL();

	public Fato_Vendas salvar(Fato_Vendas fatoVendasId);

	public Fato_Vendas getByPk(FatoVendasId id);

	public void atualizar(Fato_Vendas vendaAlterada);
}
