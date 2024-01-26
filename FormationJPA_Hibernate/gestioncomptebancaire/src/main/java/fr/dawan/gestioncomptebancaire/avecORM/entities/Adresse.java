package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;

@Entity
public class Adresse implements Serializable{

	private static final long serialVersionUID = -8868103379654024303L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	private String voie;
	
	private String numeroVoie;
	
	@OneToMany(mappedBy = "adresse")
	private Collection<UtilisateurAdresse> utilisateurAdresseList;
	
	//Possibilité de mette @ManyToOne
	@OneToOne
	@JoinColumn(name="ville")
	private Ville ville;
	
	
	public Adresse() {
		// TODO Auto-generated constructor stub
	}


	public Adresse(String voie, String numeroVoie, Ville ville) {
		super();
		this.voie = voie;
		this.numeroVoie = numeroVoie;
		this.ville = ville;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getVoie() {
		return voie;
	}


	public void setVoie(String voie) {
		this.voie = voie;
	}


	public String getNumeroVoie() {
		return numeroVoie;
	}


	public void setNumeroVoie(String numeroVoie) {
		this.numeroVoie = numeroVoie;
	}


	public Collection<UtilisateurAdresse> getUtilisateurAdresseList() {
		return utilisateurAdresseList;
	}


	public void setUtilisateurAdresseList(Collection<UtilisateurAdresse> utilisateurAdresseList) {
		this.utilisateurAdresseList = utilisateurAdresseList;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
	
}
