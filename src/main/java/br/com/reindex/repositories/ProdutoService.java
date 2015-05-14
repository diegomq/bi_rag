package br.com.reindex.repositories;

import java.util.List;

import br.com.reindex.domain.Dim_Produto;


public interface ProdutoService  {

	public Dim_Produto create(Dim_Produto produto);

	public List<Dim_Produto> getAll();

	public Dim_Produto getBy(Long id);

	public void salvar(Dim_Produto produtoAlterado);

	public void atualizar(Dim_Produto produtoOriginal);
	
	public List<Dim_Produto> getProdutosEmLojaComEstoqueAtualMaiorQueZero();
}
