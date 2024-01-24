package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;

public interface ICompteRepositoryOld {

	void AddCompte(Compte compte);
	Compte findCompteById(String numCompte);
	void deleteCompteById(String numCompte);
	void updateCompte(Compte compte);
	List<Compte> getAllCompte();
	
	List<Compte> findAll(int begin, int nbResult);
	
}
