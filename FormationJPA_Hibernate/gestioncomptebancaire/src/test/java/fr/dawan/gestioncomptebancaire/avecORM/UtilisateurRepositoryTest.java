package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IUtilisateurRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IUtilisateurRepositoryOld;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.UtilisateurRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.UtilisateurRepositoryOld;
import fr.dawan.gestioncomptebancaire.tools.EmailGenerator;

class UtilisateurRepositoryTest {

	private IUtilisateurRepository repository;
	private IUtilisateurRepositoryOld repositoryOld;

	@BeforeEach
	void setUp() throws Exception {
		repository = new UtilisateurRepository();
		repositoryOld = new UtilisateurRepositoryOld();
	}

	@Test
	void testAddUser() {

		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Carpentier");
		user.setPrenom("Thomas");

		//String generatedEmail1 = EmailGenerator.generateEmail(user.getNom(), user.getPrenom());	
		user.setEmail("cThomas100@gmailcom");

		//Aappeler la méthode addUser et verifier si elle fonctionne correctement 
		repositoryOld.addUser(user);  //(Etat de l'objet : Managé)

		//Récuperer l'ID de l'utilisateur 
		Long userIdFromDb = user.getId();

		//Verifier que l'utilisateur a été ajouté avec succès
		Utilisateur userFromDB = repositoryOld.findUserById(userIdFromDb);

		//assurer que l'utilisateur a été correctement ajouté 
		assertNotNull(userFromDB);
		assertEquals("Carpentier", userFromDB.getNom());
		assertEquals(user.getEmail(), userFromDB.getEmail());
	}

	@Test
	void testFindUserById() {
		fail("Not yet implemented");
	}



	@Test
	void testDeleteUserById() {
		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Petit");
		user.setPrenom("Jean");
		user.setEmail("pJean10.gmail.com");

		repositoryOld.addUser(user);

		Long userIdToDelete = user.getId();

		repositoryOld.deleteUserById(userIdToDelete);

		Utilisateur userFromDb = repositoryOld.findUserById(userIdToDelete);

		assertNull(userFromDb);
	}

	@Test
	void testUpdateUser() throws Exception {
		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Deschamps");
		user.setPrenom("David");
		user.setEmail("DDeschamps120@gmail.col");

		repositoryOld.addUser(user);

		user.setNom("Dupont");
		user.setPrenom("Marie");

		String generatedEmail2 = EmailGenerator.generateEmail(user.getNom(), user.getPrenom());	
		user.setEmail(generatedEmail2);

		user.setEmail(generatedEmail2);

		//Mettre à jour l'utilisateur
		repositoryOld.updateUser(user);

		Utilisateur updateUser = repositoryOld.findUserById(user.getId());

		assertNotNull(updateUser);
		assertEquals("Dupont", updateUser.getNom());
		assertEquals(user.getEmail(), updateUser.getEmail());

	}

	@Test
	void testGetAllUsers() {
		List<Utilisateur> users = repositoryOld.getAllUsers();

		assertNotNull(users);

		assertTrue(users.size() > 0);
	}

	@Test
	void testFindAll() {
		List<Utilisateur> users = repository.findAll(0, 2);

		assertEquals(2, users.size());

		for (Utilisateur user : users) {
			assertNotNull(user.getId());
			assertNotNull(user.getNom());
		}
	}


	@Test
	void testConcurrentUserUpdate() throws Exception {
		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Luc");
		user.setPrenom("Harne");
		user.setEmail("lHarne603.gmail.com");

		repositoryOld.addUser(user);

		Long userId = user.getId();

		Utilisateur userFromDb1 = repositoryOld.findUserById(userId);
		Utilisateur userFromDb2 = repositoryOld.findUserById(userId);

		assertNotNull(userFromDb1);
		assertNotNull(userFromDb2);

		//Utilisateur 1 met à jour le nom 
		userFromDb1.setNom("Lucien");

		//Utilisateur 2 met à jour le nom 
		userFromDb2.setNom("Jean Luc");

		//Sauvegarde des modifications des deux utilisateurs 
		repositoryOld.updateUser(userFromDb1);

		assertThrows(Exception.class, () -> repositoryOld.updateUser(userFromDb2));

	}


	

}
