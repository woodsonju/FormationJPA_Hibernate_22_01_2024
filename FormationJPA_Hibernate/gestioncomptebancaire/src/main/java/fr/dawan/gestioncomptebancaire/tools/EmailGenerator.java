package fr.dawan.gestioncomptebancaire.tools;

public class EmailGenerator {
	
	private static int emailCounter; 
	
	
	public static String generateEmail(String prefix, String suffix) {
		emailCounter++;
		System.out.println("dzdzdzdzd : " + emailCounter);
		String generatedEmail = prefix.toLowerCase() + "." + suffix.toLowerCase() + emailCounter + "@gmail.com";
		return generatedEmail;
	}
	



}
