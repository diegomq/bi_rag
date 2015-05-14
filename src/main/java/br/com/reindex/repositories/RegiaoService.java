package br.com.reindex.repositories;

import java.util.List;

import br.com.reindex.domain.Dim_Regiao;

public interface RegiaoService {

	public List<Dim_Regiao> getAll();

	public Dim_Regiao getBy(Long id);

	public Dim_Regiao salvar(Dim_Regiao produtoAlterado);

	public void atualizar(Dim_Regiao produtoOriginal);
}
