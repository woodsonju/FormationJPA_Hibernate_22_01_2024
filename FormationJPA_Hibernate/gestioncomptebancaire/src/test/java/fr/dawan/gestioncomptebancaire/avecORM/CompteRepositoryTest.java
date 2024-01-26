package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;
import fr.dawan.gestioncomptebancaire.avecORM.entities.CompteCourant;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.CompteRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.ICompteRepository;
import fr.dawan.gestioncomptebancaire.tools.RandomStringGenerator;

class CompteRepositoryTest {
	
	private ICompteRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		repository = new CompteRepository();
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

//	@Test
//	void testSave() {
//		String numCompte = RandomStringGenerator.generateRandomString();
//		Compte compte = new Compte();
//		compte.setNumCompte(numCompte);
//		compte.setSolde(1000.0);
//		compte.setDateCreation(LocalDate.now());
//		
//		repository.save(compte);
//		
//		Compte retrieveCompte = repository.findById(Compte.class, compte.getNumCompte());
//		
//		assertNotNull(retrieveCompte);
//		assertEquals(1000.0, retrieveCompte.getSolde());
//	}
	

	//Tester avec compte courant
	@Test
	void testSave() {
		String numCompte = RandomStringGenerator.generateRandomString();
		Compte compte = new CompteCourant();
		
		compte.setNumCompte(numCompte);
		compte.setSolde(6500.0);
		compte.setDateCreation(LocalDate.now());
		
		
		repository.save(compte);
		
		Compte retrieveCompte = repository.findById(Compte.class, compte.getNumCompte());
		
		assertNotNull(retrieveCompte);
		assertEquals(1000.0, retrieveCompte.getSolde());
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}
	
	@Test 
	void testFindByDateCreation() {
		String numCompte = RandomStringGenerator.generateRandomString();
		Compte compte = new Compte();
		compte.setNumCompte(numCompte);
		compte.setSolde(35000.0);
		
		LocalDate dateCreation = LocalDate.of(2023, 1, 1);
		compte.setDateCreation(dateCreation);
		
		repository.save(compte);
		
		List<Compte> comptes = repository.findByDateCreation(dateCreation);
		
		assertNotNull(comptes);
		assertTrue(comptes.size() > 0);
	}

}
