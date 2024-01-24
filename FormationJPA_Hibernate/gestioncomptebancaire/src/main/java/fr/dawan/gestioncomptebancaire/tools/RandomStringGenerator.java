package fr.dawan.gestioncomptebancaire.tools;

import java.util.Random;

public class RandomStringGenerator {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int NUMERO_COMPTE_LENGHT = 11;
	
	public static String generateRandomString() {
		Random random = new Random();
		StringBuilder stb = new StringBuilder(NUMERO_COMPTE_LENGHT);
		
		for (int i = 0; i < NUMERO_COMPTE_LENGHT; i++) {
			
			//Genère un indice aléatoire pour choisir un caractère dans CHARACTERS
			//exemple : position 7
			int randomIndex = random.nextInt(CHARACTERS.length());
			
			//Récupère le caractère correspondant à l'indice (position) 
			//ex : caractère H
			char randomChar = CHARACTERS.charAt(randomIndex);
			
			//ajoute le caractère  à la chaine 
			stb.append(randomChar);
		}
		
		return stb.toString();
	}

}
