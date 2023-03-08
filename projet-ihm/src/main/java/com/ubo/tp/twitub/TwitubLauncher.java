package main.java.com.ubo.tp.twitub;

import main.java.com.ubo.tp.twitub.core.Twitub;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitubLauncher {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Twitub twitub = new Twitub();
		twitub.initDirectory("C:\\Users\\hould\\eclipse-workspace\\projet-meta.zip_expanded\\projet-ihm\\src\\main\\resources\\twits");
		twitub.show();
	}

}
