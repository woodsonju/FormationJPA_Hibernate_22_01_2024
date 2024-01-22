package fr.dawan.gestioncomptebancaire.sansORM;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.sansORM.dao.ConnexionBDD;
import fr.dawan.gestioncomptebancaire.sansORM.dao.IUtilisateurDAO;
import fr.dawan.gestioncomptebancaire.sansORM.dao.UtilisateurDAO;
import fr.dawan.gestioncomptebancaire.sansORM.model.Utilisateur;

class UtilisateurDAOTest {
	
	private Connection cnx; 
	private IUtilisateurDAO dao;

	@BeforeEach
	void setUp() {
		cnx = ConnexionBDD.getConnection();
		dao = new UtilisateurDAO();
	}
	
	
	
	@Test
	void testAddUser() throws SQLException {
		Utilisateur user1 = new Utilisateur("Dupont", "Cedric", "cedric@gmail.com");
		dao.addUser(user1, cnx);
		
		Utilisateur user2 = new Utilisateur("Laguerre", "Sandrine", "sandrine@gmail.com");
		dao.addUser(user2, cnx);
		
		Utilisateur user3 = new Utilisateur("Robert", "Pierre", "pierre@gmail.com");
		dao.addUser(user3, cnx);
		
		List<Utilisateur> users = dao.getAllUsers(cnx);
		
		assertTrue(users.size() > 0);
		
	}

}
