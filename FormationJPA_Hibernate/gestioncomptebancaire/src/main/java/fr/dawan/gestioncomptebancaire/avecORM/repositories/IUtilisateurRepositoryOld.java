package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;

public interface IUtilisateurRepositoryOld {

	void addUser(Utilisateur user);
	Utilisateur findUserById(Long id);
	void deleteUserById(Long id);
	void updateUser(Utilisateur user) throws Exception;
	List<Utilisateur> getAllUsers();
	
	List<Utilisateur> findAll(int begin, int nbResult);
	
}
