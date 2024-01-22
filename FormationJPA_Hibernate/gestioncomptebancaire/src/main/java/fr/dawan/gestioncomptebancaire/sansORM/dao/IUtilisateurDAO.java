package fr.dawan.gestioncomptebancaire.sansORM.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.dawan.gestioncomptebancaire.sansORM.model.Utilisateur;

/*
 * Fonctionnalité gestion des utilisateurs 
 * 		- Ajouter un utilisateur 
 * 		- Récuperer un utilisateur 
 * 		- Effacer un utilisateur 
 * 		- Recuperer la liste des utilisateurs 
 * 		- Mettre à jour un utilisateur
 */
public interface IUtilisateurDAO {

	void addUser(Utilisateur user, Connection cnx) throws SQLException;
	Utilisateur findUser(int id, Connection cnx) throws SQLException;
	void deleteUser(int id, Connection cnx) throws SQLException;
	List<Utilisateur> getAllUsers(Connection cnx) throws SQLException;
	void updateUser(Utilisateur user, Connection cnx) throws SQLException;
	
}
