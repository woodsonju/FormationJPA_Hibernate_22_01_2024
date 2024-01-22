package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ConnexionTest {

	@Test
	void testConnexionBDD() {
		//Creer l'entityManagerFactory à partir de l'unité de persistence définie dans le 
		//fichier persistencexml
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("gestionCB_mysql");
		
		//Créer l'entityManager 
		EntityManager entityManager =  emf.createEntityManager();
		
		assertTrue(entityManager!=null);
	}

}
