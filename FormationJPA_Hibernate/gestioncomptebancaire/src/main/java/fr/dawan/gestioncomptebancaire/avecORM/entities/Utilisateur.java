package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	@Id
	@GeneratedValue
	private Long id;
	
	private String nom; 
	private String prenom;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
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

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
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
