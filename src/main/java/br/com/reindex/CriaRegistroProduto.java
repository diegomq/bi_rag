package br.com.reindex;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.enums.Tipo_Produto;

public class CriaRegistroProduto {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dmVenda");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		
		Dim_Produto produto = new Dim_Produto("A", 0f, new Date(), 100l,  false, Tipo_Produto.CARTA);
		manager.persist(produto);
		
		manager.remove(produto);
		
		trx.commit();
		manager.close();
	}

}
