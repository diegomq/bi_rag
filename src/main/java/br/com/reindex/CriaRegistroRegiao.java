package br.com.reindex;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.reindex.domain.Dim_Regiao;

public class CriaRegistroRegiao {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dmVenda");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Dim_Regiao regiao = new Dim_Regiao("Prontera", "lado do eden");
		manager.persist(regiao);
		
		manager.remove(regiao);
		trx.commit();
		manager.close();
	}

}
