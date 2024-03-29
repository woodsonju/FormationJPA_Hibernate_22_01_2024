package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;
import fr.dawan.gestioncomptebancaire.avecORM.entities.CompteCourant;
import fr.dawan.gestioncomptebancaire.avecORM.entities.CompteEpargne;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Role;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import fr.dawan.gestioncomptebancaire.avecORM.entities.UtilisateurDetail;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IUtilisateurRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IUtilisateurRepositoryOld;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.UtilisateurRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.UtilisateurRepositoryOld;
import fr.dawan.gestioncomptebancaire.tools.EmailGenerator;
import fr.dawan.gestioncomptebancaire.tools.RandomStringGenerator;

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
		user.setEmail("cThomas101@gmailcom");

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
		//IUtilisateurRepositoryOld
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

	@Test
	void testFindByEmail() {
		// Créer un utilisateur pour le tester
		Utilisateur user = new Utilisateur("Zishan", "Ethan", "ethan3@gmail.com");
		repository.save(user);

		// Appeler la méthode findByEmail pour récupérer l'utilisateur
		Utilisateur foundUser = repository.findByEmail("ethan3@gmail.com");

		// Vérifier que l'utilisateur a été trouvé
		assertNotNull(foundUser);
		assertEquals("Zishan", foundUser.getNom());
		assertEquals("Ethan", foundUser.getPrenom());
		assertEquals("ethan3@gmail.com", foundUser.getEmail());

	}
	
	@Test
	void testSaveUser() {

		//Créer un objet utilisateur pour le test  (Etat de l'objet : Transient)
		Utilisateur user = new Utilisateur();
		user.setNom("Carpentier");
		user.setPrenom("Thomas");

		//String generatedEmail1 = EmailGenerator.generateEmail(user.getNom(), user.getPrenom());	
		user.setEmail("cThomas180@gmailcom");

		//Aappeler la méthode addUser et verifier si elle fonctionne correctement 
		repository.save(user);  //(Etat de l'objet : Managé)

		//Récuperer l'ID de l'utilisateur 
		Long userIdFromDb = user.getId();

		//Verifier que l'utilisateur a été ajouté avec succès
		Utilisateur userFromDB = repository.findById(Utilisateur.class, userIdFromDb);

		//assurer que l'utilisateur a été correctement ajouté 
		assertNotNull(userFromDB);
		assertEquals("Carpentier", userFromDB.getNom());
		assertEquals(user.getEmail(), userFromDB.getEmail());
	}

	
	@Test
	void testSaveUserWithComptes_SansCascadePersist() {
		
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail("54871656", "18-05-2012", "F");
		Utilisateur user = new Utilisateur("Henri", "Pauline", "hPauline888@gmail.com", utilisateurDetail);
		
		//Generer des numeros de compte 
		String numCompte1 = RandomStringGenerator.generateRandomString();
		String numCompte2 = RandomStringGenerator.generateRandomString();
		
		//Créer les comptes associés 
		Compte cb1 = new CompteCourant(numCompte1, LocalDate.now(), 1000.0, user, 300);
		Compte cb2 = new CompteEpargne(numCompte2, LocalDate.of(2023, 9, 17), 5200.0, user, 0.7);
		
		//Ajouter les comptes à la liste 
		List<Compte> comptes = new ArrayList<Compte>();
		comptes.add(cb1);
		comptes.add(cb2);
		
		//Associer la liste des comptes à l'utilisateur
		user.setComptes(comptes);
		
		//Ajouter l'utilisateur avec ses comptes associées 
		repository.saveUserWithComptes(user);
		
		//Verifier que l'utilisateur a bien été ajouté 
		
		assertNotNull(user.getId());
	}
	
	@Test
	void testSaveUserWithComptes_AvecCascadePersist() {
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail("8521555", "11-12-2002", "M");
		Utilisateur user = new Utilisateur("Henry", "Paul", "hPaul696@gmail.com", utilisateurDetail);
		
		//Generer des numeros de compte 
		String numCompte1 = RandomStringGenerator.generateRandomString();
		String numCompte2 = RandomStringGenerator.generateRandomString();
		
		//Créer les comptes associés 
		Compte cb1 = new CompteEpargne(numCompte1, LocalDate.now(), 3500.0, user, 0.5);
		Compte cb2 = new CompteCourant(numCompte2, LocalDate.of(2000, 5, 27), 68000.0, user, 300.0);
		
		List<Compte> comptes = new ArrayList<Compte>();
		comptes.add(cb1);
		comptes.add(cb2);
		
		//Associer la liste des comptes à l'utilisateur
		user.setComptes(comptes);
		
		repository.save(user);
		
		//Verifier que l'utilisateur a bien été ajouté
		assertNotNull(user.getId());
		
		//Verifier que les comptes ont été ajouté 
		assertNotNull(cb1.getNumCompte());
		assertNotNull(cb2.getNumCompte());
		
		//Verifier que les comptes sont associés à l'utilisateur 
		assertSame(user, cb1.getClient());
		assertSame(user, cb2.getClient());
		
	}
	
	
	@Test 
	void testFindById() {
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail("8521555", "11-12-2002", "M");
		Utilisateur user = new Utilisateur("Laguerre", "Mateo", "mLaguerre355@gmail.com", utilisateurDetail);

		//Generer des numeros de compte 
		String numCompte1 = RandomStringGenerator.generateRandomString();
		String numCompte2 = RandomStringGenerator.generateRandomString();
		
		//Créer les comptes associés 
		Compte cb1 = new CompteCourant(numCompte1, LocalDate.now(), 3500.0, user, 100);
		Compte cb2 = new CompteEpargne(numCompte2, LocalDate.of(2000, 5, 27), 68000.0, user, 0.8);
		
		List<Compte> comptes = new ArrayList<Compte>();
		comptes.add(cb1);
		comptes.add(cb2);
		
		//Associer la liste des comptes à l'utilisateur
		user.setComptes(comptes);
		
		//save l'utilisateur avec les comptes associés 
		repository.save(user);
		
		//Récuperer l'tuilisateur
		Utilisateur userBDD = repository.findById(Utilisateur.class, user.getId());
		
		//Verifier que l'utilisateur a bien été ajouté
		assertNotNull(user.getId());
		
		assertNotNull(user.getComptes());
		
		assertEquals(2, userBDD.getComptes().size());
		
		//Verifier que les comptes sont correctement associés à l'utilisateur 
		for (Compte compte : comptes) {
			assertSame(user, compte.getClient());
		}

	}
	
	
	@Test 
	void testFindUserWithComptes() {
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail("8521555", "11-12-2002", "M");
		Utilisateur user = new Utilisateur("Laguerre", "Mateo", "mLaguerre777@gmail.com", utilisateurDetail);

		//Generer des numeros de compte 
		String numCompte1 = RandomStringGenerator.generateRandomString();
		String numCompte2 = RandomStringGenerator.generateRandomString();
		
		//Créer les comptes associés 
		Compte cb1 = new CompteCourant(numCompte1, LocalDate.now(), 3500.0, user, 300);
		Compte cb2 = new CompteEpargne(numCompte2, LocalDate.of(2000, 5, 27), 68000.0, user, 0.9);
		
		List<Compte> comptes = new ArrayList<Compte>();
		comptes.add(cb1);
		comptes.add(cb2);
		
		//Associer la liste des comptes à l'utilisateur
		user.setComptes(comptes);
		
		//save l'utilisateur avec les comptes associés 
		repository.save(user);
		
		//Récuperer l'tuilisateur
		Utilisateur userBDD = repository.findUserWithComtes(user.getId());
		
		//Verifier que l'utilisateur a bien été ajouté
		assertNotNull(user.getId());
		
		assertNotNull(user.getComptes());
		
		assertEquals(2, userBDD.getComptes().size());
		
		//Verifier que les comptes sont correctement associés à l'utilisateur 
		for (Compte compte : comptes) {
			assertEquals(user, compte.getClient());
		}
	}
	
	
	@Test
	void testSaveUserWithRoles() {
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail("698555", "18-10-1996", "F");
		Utilisateur user = new Utilisateur("Laguerre", "Julie", "lJulie@gmail.com", utilisateurDetail);

		
		Role role1 = new Role("Admininstrateur");
		Role role2 = new Role("DEV");
		Role role3 = new Role("Tech System");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		
		user.setRoles(roles);
		
		repository.save(user);
		
		assertNotNull(user.getId());
		
		//Verifier que les langues ont ete ajoutées
		assertNotNull(role1.getRoleId());
		assertNotNull(role2.getRoleId());
		assertNotNull(role3.getRoleId());
		
	}
	

}
