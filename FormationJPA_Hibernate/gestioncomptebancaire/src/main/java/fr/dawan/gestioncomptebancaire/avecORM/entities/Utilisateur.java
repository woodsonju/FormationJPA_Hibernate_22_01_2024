package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="t_User")
public class Utilisateur implements Serializable{


	private static final long serialVersionUID = -5180184542773132913L;
	
	/*
	 * L'annotation @Id permet de specifier que l'attribut id sera la clé primaire dans la table 
	 * 
	 * Si on veut que la clé primaire soit autogénéré (auto-incrementé) on utilise 
	 * l'annotation @GeneratedValue
	 */

//	@GeneratedValue(strategy = GenerationType.TABLE,  generator = "table-generator")
//	@TableGenerator(name = "table-generator", 
//			table="user_ids", 
//			pkColumnName = "seq_id", 
//			valueColumnName = "seq_value")
	

//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
//	@SequenceGenerator(
//			name="sequence-generator",
//			sequenceName = "user_sequence",
//			allocationSize = 5
//			)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom; 
	private String prenom;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	
	@Version
	private int version;
	
	//Ajouter lors de la relation @OneToOne
	@Embedded
	private UtilisateurDetail utilisateurDetail;
	
	//L'attribut mappedBy, doit référencer le champ qui porte la relation 
	//côté entité propriétaire. C'est à dire le champs personne
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Collection<Compte> comptes;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	

	public Utilisateur(String nom, String prenom, String email, UtilisateurDetail utilisateurDetail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.utilisateurDetail = utilisateurDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", version=" + version
				+ ", utilisateurDetail=" + utilisateurDetail + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}


}
