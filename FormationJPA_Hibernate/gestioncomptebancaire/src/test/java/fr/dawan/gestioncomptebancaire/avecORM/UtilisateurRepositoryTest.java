package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IUtilisateurRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.UtilisateurRepository;

class UtilisateurRepositoryTest {
	
	private IUtilisateurRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		repository = new UtilisateurRepository();
	}

	@Test
	void testAddUser() {
		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Carpentier");
		user.setPrenom("Thomas");
		user.setEmail("cThomas.gmail.com");
		
		//Aappeler la méthode addUser et verifier si elle fonctionne correctement 
		repository.addUser(user);  //(Etat de l'objet : Managé)
		
		//TODO 
		
		
	}

	@Test
	void testFindUserById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUserById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

}
