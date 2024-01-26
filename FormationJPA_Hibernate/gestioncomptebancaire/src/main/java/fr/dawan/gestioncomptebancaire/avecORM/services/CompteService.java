package fr.dawan.gestioncomptebancaire.avecORM.services;

import java.time.LocalDate;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;
import fr.dawan.gestioncomptebancaire.avecORM.entities.CompteCourant;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Operation;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Retrait;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Versement;
import fr.dawan.gestioncomptebancaire.avecORM.exceptions.CompteIntrouvableException;
import fr.dawan.gestioncomptebancaire.avecORM.exceptions.SoldeInsuffisantException;
import fr.dawan.gestioncomptebancaire.avecORM.exceptions.VirementMemeCompteException;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.CompteRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.ICompteRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.IOperationRepository;
import fr.dawan.gestioncomptebancaire.avecORM.repositories.OperationRepository;

public class CompteService implements ICompteService {


	private ICompteRepository compteRepository ;
	private IOperationRepository operationRepository;

	public CompteService() {
		compteRepository = new CompteRepository();
		operationRepository = new OperationRepository();
	}


	@Override
	public Compte consulter(String numCompte) throws Exception {
		Compte compte = compteRepository.findById(Compte.class, numCompte);
		if(compte == null) {
			throw new CompteIntrouvableException("Compte Introuvable");
		}
		return compte;
	}

	@Override
	public void verser(String numCompte, double montant) throws Exception {
		Compte compte = consulter(numCompte);
		Versement versement = new Versement(LocalDate.now(), montant, compte);
		operationRepository.save(versement);
		compte.setSolde(compte.getSolde() + montant);
		compteRepository.update(compte);	
	}

	@Override
	public void retirer(String numCpte, double montant) throws Exception {
		Compte compte = consulter(numCpte);
		double facilitesCaisse = 0;
		
		//Si c'est un compte courant on recupère le decouvert
		if(compte instanceof CompteCourant) {
			facilitesCaisse = ((CompteCourant) compte).getDecouvert();
		}
		if(compte.getSolde() + facilitesCaisse < montant) {
			throw new SoldeInsuffisantException("Solde insuffisant");
		}
		Retrait retrait = new Retrait(LocalDate.now(), montant, compte);
		operationRepository.save(retrait);
		compte.setSolde(compte.getSolde() - montant);
		compteRepository.update(compte);	

	}

	@Override
	public void virement(String numCpt1, String numCpt2, double montant) throws Exception {
		if(numCpt1.equals(numCpt2)) {
			throw new VirementMemeCompteException("Impossible d'effectuer un virement sur le même compte");
		}
		
		retirer(numCpt1, montant);
		verser(numCpt2, montant);

	}

}
