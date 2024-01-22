package fr.dawan.gestioncomptebancaire.sansORM.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.sansORM.model.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilisateurDAO.class);

	@Override
	public void addUser(Utilisateur user, Connection cnx) throws SQLException {
		String sql = "INSERT INTO utilisateurs(nom, prenom, email) VALUES(?, ?, ?)";
		
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setString(1, user.getNom());
		ps.setString(2, user.getPrenom());
		ps.setString(3, user.getEmail());
		
		ps.executeUpdate();
		
		ps.close();

	}

	@Override
	public Utilisateur findUser(int id, Connection cnx) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void deleteUser(int id, Connection cnx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utilisateur> getAllUsers(Connection cnx) throws SQLException {
		List<Utilisateur> listUser = new ArrayList<Utilisateur>();
		String sql = "SELECT * FROM utilisateurs";
		
		try(PreparedStatement ps = cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				Utilisateur u = new Utilisateur();
				u.setId(rs.getInt("id"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				
				listUser.add(u);
			}
		}
		logger.info("" + listUser);
		return listUser;
	}

	@Override
	public void updateUser(Utilisateur user, Connection cnx) throws SQLException {
		
		String sql = "UPDATE utilisateurs SET nom=?, prenom=?, email=? WHERE id=?";
		
		try(PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getId());
			
			ps.executeUpdate();
		}
		
	}

	

}
