package br.com.reindex;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.domain.Dim_Regiao;
import br.com.reindex.domain.Dim_Tempo;
import br.com.reindex.domain.Fato_Vendas;
import br.com.reindex.enums.Tipo_Produto;

public class CriaFato {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dmVenda");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Dim_Tempo dim_tempo = manager.find(Dim_Tempo.class, 1l);
		
		Dim_Produto produto = new Dim_Produto("A", 1f,new Date(), 100l,  false, Tipo_Produto.CARTA);
		manager.persist(produto);
		
		Dim_Regiao regiao = new Dim_Regiao("Prontera", "lado do eden");
		manager.persist(regiao);
		
		Fato_Vendas venda = new Fato_Vendas(produto, dim_tempo,regiao, 100l);  
		manager.persist(venda);
		
		manager.remove(regiao);
		manager.remove(produto);
		manager.remove(venda);
		
		trx.commit();
		manager.close();
	}
}
