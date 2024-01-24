//package fr.dawan.gestioncomptebancaire.avecORM.entities;
//
//import java.util.Objects;
//
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//
//@Entity
//public class UtilisateurDetail3 {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String num_secu;
//	private String dateNaissance;
//	private String sexe;
//	
//	@OneToOne(mappedBy = "utilisateurDetail3")
//	private Utilisateur3 utilisateur;
//	
//	public UtilisateurDetail3() {
//	}
//
//	public UtilisateurDetail3(String num_secu, String dateNaissance, String sexe) {
//		super();
//		this.num_secu = num_secu;
//		this.dateNaissance = dateNaissance;
//		this.sexe = sexe;
//	}
//
//	public String getNum_secu() {
//		return num_secu;
//	}
//
//	public void setNum_secu(String num_secu) {
//		this.num_secu = num_secu;
//	}
//
//	public String getDateNaissance() {
//		return dateNaissance;
//	}
//
//	public void setDateNaissance(String dateNaissance) {
//		this.dateNaissance = dateNaissance;
//	}
//
//	public String getSexe() {
//		return sexe;
//	}
//
//	public void setSexe(String sexe) {
//		this.sexe = sexe;
//	}
//
//	@Override
//	public String toString() {
//		return "UtilisateurDetail [num_secu=" + num_secu + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(dateNaissance, num_secu, sexe);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UtilisateurDetail3 other = (UtilisateurDetail3) obj;
//		return Objects.equals(dateNaissance, other.dateNaissance) && Objects.equals(num_secu, other.num_secu)
//				&& Objects.equals(sexe, other.sexe);
//	}
//	
//	
//
//}
