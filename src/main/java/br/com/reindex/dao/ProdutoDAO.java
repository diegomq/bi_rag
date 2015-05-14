package br.com.reindex.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.repositories.ProdutoService;
import br.com.reindex.suri.framework.dao.DaoSupport;

@Service
@Component
public class ProdutoDAO extends DaoSupport<Dim_Produto, Serializable> implements ProdutoService{

	@Override
	@Transactional
	public Dim_Produto create(Dim_Produto produto) {
		Dim_Produto produtoASerSalvo = new Dim_Produto(produto.getNome(), produto.getValor(), produto.getDateInicio(), produto.getQuantidadeEstoqueInicial(), produto.isEmLoja(), produto.getTipoProduto());
		save(produtoASerSalvo);
		return produtoASerSalvo;
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Dim_Produto> getAll() {
		Criteria criteria = getSession().createCriteria(Dim_Produto.class);
		criteria.addOrder(Order.asc(Dim_Produto.NOME));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	@Transactional
	public Dim_Produto getBy(Long id) {
		return retrieve(id);
	}

	@Override
	@Transactional
	public void salvar(Dim_Produto produto) {
		save(produto);
		
	}

	@Override
	@Transactional
	public void atualizar(Dim_Produto produto) {
		update(produto);
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Dim_Produto> getProdutosEmLojaComEstoqueAtualMaiorQueZero() {
		Criteria criteria = getSession().createCriteria(Dim_Produto.class);
		criteria.add(Restrictions.eq(Dim_Produto.EM_LOJA, true));
		criteria.add(Restrictions.gt(Dim_Produto.ESTOQUE_ATUAL, 0l));
		Date agora = new Date();
		criteria.add(Restrictions.lt(Dim_Produto.DATA_INICIO, agora));
		criteria.add(Restrictions.gt(Dim_Produto.DATA_FIM, agora));
		criteria.addOrder(Order.asc(Dim_Produto.NOME));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}


}
