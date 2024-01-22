package fr.dawan.gestioncomptebancaire.sansORM;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.sansORM.dao.ConnexionBDD;

class ConnexionBDDTest {

	@Test
	void testConnexionBDD() {
		// Appel initial pour obtenir la première instance de connexion
		Connection firstInstance =   ConnexionBDD.getConnection();
		// Appel ultérieur pour obtenir une autre instance de connexion
		Connection secondInstance = ConnexionBDD.getConnection();
		
		assertTrue(firstInstance != null);
		// Vérifier que les deux instances sont les mêmes, montrant ainsi le modèle singleton
		assertSame(firstInstance, secondInstance, "Les instances de connexion ne sont pas les mêmes");
	}
}


