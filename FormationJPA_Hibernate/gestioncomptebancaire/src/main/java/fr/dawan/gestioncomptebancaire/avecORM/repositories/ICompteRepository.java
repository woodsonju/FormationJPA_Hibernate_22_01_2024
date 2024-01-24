package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;

public interface ICompteRepository extends IGenericRepository<Compte, String>{

	List<Compte> findAll(int begin, int nbResult);
	
}
