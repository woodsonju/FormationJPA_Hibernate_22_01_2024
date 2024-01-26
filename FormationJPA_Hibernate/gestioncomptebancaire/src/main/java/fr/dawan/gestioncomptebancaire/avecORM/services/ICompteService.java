package fr.dawan.gestioncomptebancaire.avecORM.services;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;

public interface ICompteService {
	
	Compte consulter(String numCompte) throws Exception ;
	void verser(String numCompte, double montant) throws Exception; 
	void retirer(String numCpte, double montant) throws Exception;
	void virement(String numCpt1, String numCpt2, double montant) throws Exception;

}
