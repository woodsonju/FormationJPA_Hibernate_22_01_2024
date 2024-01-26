package fr.dawan.gestioncomptebancaire.avecORM;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Role;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IRoleRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.RoleRepository;

class RoleRepositoryTest {

	private static IRoleRepository roleRepository;
	
	@BeforeAll
	static void setUp() throws Exception {
		roleRepository = new RoleRepository();
	}

	@Test
	void testFindRoleByContaing() {
		
		String keyword ="on";
		
		List<Role> roles = roleRepository.findRoleByContaing(keyword);
		
		//assert
		assertTrue(roles.size() > 0, "La liste des roles doit pas être vide");
		for (Role role : roles) {
			assertTrue(role.getNom().toLowerCase().contains(keyword.toLowerCase()), 
					"Le nom role doit contenir le mot clé");
		}
		
	}



	@Test
	void testSave() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(createRole("Admin"));
		roles.add(createRole("User"));
		roles.add(createRole("Conseiller Financier"));
		roles.add(createRole("Technicien système"));
		roles.add(createRole("Gestionnaire de compte"));
		
		for (Role role : roles) {
			roleRepository.save(role);
		}
			
		//Assert 
		for (Role role : roles) {
			assertNotNull(role.getRoleId(), "L'ID du role ne doit pas être null après la sauvegarde");
		}
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
	
	private Role createRole(String nom) {
		Role role = new Role();
		role.setNom(nom);
		return role;
	}

}
