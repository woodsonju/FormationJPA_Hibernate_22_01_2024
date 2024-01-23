package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;

public interface IUtilisateurRepository {

	void addUser(Utilisateur user);
	Utilisateur findUserById(Long id);
	void deleteUserById(Long id);
	void updateUser(Utilisateur user);
	List<Utilisateur> getAllUsers();
	
	List<Utilisateur> findAll(int begin, int nbResult);
	
}
