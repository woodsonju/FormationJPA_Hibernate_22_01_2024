package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;

@Entity
public class Role implements Serializable{
	
	private static final long serialVersionUID = -2109556459576832932L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@Version
	private int version;
	
	@Column(nullable = false, unique = true)
	private String nom; 
	
	private String description;
	
	
	@ManyToMany(mappedBy = "roles")
	Collection<Utilisateur> users;
	
	
	public Role() {
		// TODO Auto-generated constructor stub
	}


	public Role(String nom) {
		super();
		this.nom = nom;
	}


	public Role(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Collection<Utilisateur> getUsers() {
		return users;
	}


	public void setUsers(Collection<Utilisateur> users) {
		this.users = users;
	}


	public int getVersion() {
		return version;
	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", version=" + version + ", nom=" + nom + ", description=" + description
				+ ", users=" + users + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, nom, roleId, users);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(description, other.description) && Objects.equals(nom, other.nom)
				&& Objects.equals(roleId, other.roleId) && Objects.equals(users, other.users);
	}
	
}
