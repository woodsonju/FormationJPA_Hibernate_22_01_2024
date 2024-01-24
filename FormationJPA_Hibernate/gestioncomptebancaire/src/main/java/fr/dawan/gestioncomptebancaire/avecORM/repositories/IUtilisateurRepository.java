package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;

public interface IUtilisateurRepository extends IGenericRepository<Utilisateur, Long>{


	List<Utilisateur> findAll(int begin, int nbResult);
	

}
