package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;

import jakarta.persistence.Id;

public class Compte implements Serializable{

	private static final long serialVersionUID = -1348168111892974333L;

	@Id
	private String numCompte;
	
	
	
	
}


