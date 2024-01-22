package fr.dawan.gestioncomptebancaire.sansORM.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Singleton : 
 * Le design pattern Singleton permet de s'assurer qu'une classe ne puisse produire qu'une seule et unique instance
 */
public class ConnexionBDD {

	private static Connection cnx;
	
	private static final Logger logger = LoggerFactory.getLogger(ConnexionBDD.class);
	
	//Un constructeur privé afin d'empecher la création d'objet depuis l'exterieur de la classe
	private ConnexionBDD() {
		
	}
	
	//Le bloc static va s'executer qu'une seule fois lors du chargement de la classe 
	//Ils sont lancés avant l'appel des constructeurs
	static {
		
		Properties p = new Properties();
		
		//Try-with-ressources pour simplifier la gestion des ressources
		//Cela garantit que les ressources sont fermées correctement, même en cas 
		//d'exceptions
		try(InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
			p.load(input);
			//Charger dynamiquement le pilote JDBC 
			//Class.forName(p.getProperty("driver"));
			
			
			//Créer la connexion 
			cnx = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("pwd"));
			
			//System.out.println("Connexion : OK");
			logger.info("Connexion : OK");
			
		} catch (Exception e) {
			//System.out.println("Echec de connexion : ");
			logger.error("Echec de connexion");
			e.printStackTrace();
			throw new RuntimeException("Erreur lors de la connexion à la base de données.", e);

		}
		
	}
	
	//Une méthode statique qui permet de retourner l'unique instance créée
	public static Connection getConnection() {
		return cnx;
	}
	
}
