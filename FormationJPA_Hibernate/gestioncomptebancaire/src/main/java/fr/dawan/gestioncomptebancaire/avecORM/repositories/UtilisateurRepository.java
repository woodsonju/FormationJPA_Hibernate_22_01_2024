package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;

public class UtilisateurRepository extends GenericRepository<Utilisateur, Long> implements IUtilisateurRepository{

	@Override
	public List<Utilisateur> findAll(int begin, int nbResult) {
		// TODO Auto-generated method stub
		return null;
	}


}	
