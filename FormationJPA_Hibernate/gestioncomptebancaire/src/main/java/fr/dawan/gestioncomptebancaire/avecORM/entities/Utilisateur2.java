//package fr.dawan.gestioncomptebancaire.avecORM.entities;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embedded;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import jakarta.persistence.TableGenerator;
//import jakarta.persistence.Version;
//
//@Entity
//@Table(name="t_User")
//public class Utilisateur2 implements Serializable{
//
//
//	private static final long serialVersionUID = -5180184542773132913L;
//	
//	/*
//	 * L'annotation @Id permet de specifier que l'attribut id sera la clé primaire dans la table 
//	 * 
//	 * Si on veut que la clé primaire soit autogénéré (auto-incrementé) on utilise 
//	 * l'annotation @GeneratedValue
//	 */
//
////	@GeneratedValue(strategy = GenerationType.TABLE,  generator = "table-generator")
////	@TableGenerator(name = "table-generator", 
////			table="user_ids", 
////			pkColumnName = "seq_id", 
////			valueColumnName = "seq_value")
//	
//
////	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
////	@SequenceGenerator(
////			name="sequence-generator",
////			sequenceName = "user_sequence",
////			allocationSize = 5
////			)
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String nom; 
//	private String prenom;
//	
//	@Column(nullable = false, unique = true)
//	private String email;
//	
//	
//	@Version
//	private int version;
//	
//	//Ajouter lors de la relation @OneToOne
//	/*
//	 * L'utilisation de @OneToOne et  @MapsId est lié à la modelisation de relation entre 
//	 * entité et à la gestion des clés primaires partagées entre deux entités
//	 */
//	@OneToOne @MapsId
//	private UtilisateurDetail2 utilisateurDetail2;
//	
//	public Utilisateur2() {
//		super();
//	}
//
//	public Utilisateur2(String nom, String prenom, String email) {
//		super();
//		this.nom = nom;
//		this.prenom = prenom;
//		this.email = email;
//	}
//	
//	
//
//	public Utilisateur2(String nom, String prenom, String email, UtilisateurDetail2 utilisateurDetail2) {
//		super();
//		this.nom = nom;
//		this.prenom = prenom;
//		this.email = email;
//		this.utilisateurDetail2 = utilisateurDetail2;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNom() {
//		return nom;
//	}
//
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//
//	public String getPrenom() {
//		return prenom;
//	}
//
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//	
//
//	@Override
//	public String toString() {
//		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", version=" + version
//				+ ", utilisateurDetail=" + utilisateurDetail2 + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(email, id, nom, prenom);
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
//		Utilisateur2 other = (Utilisateur2) obj;
//		return Objects.equals(email, other.email) && id == other.id && Objects.equals(nom, other.nom)
//				&& Objects.equals(prenom, other.prenom);
//	}
//
//
//}
