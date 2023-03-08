package main.java.com.ubo.tp.twitub.ihm;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class TwitubMock {

	/**
	 * Fenetre du bouchon
	 */
	protected JFrame mFrame;

	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */
	public TwitubMock(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		// Init auto de l'IHM au cas ou ;)
		if (mFrame == null) {
			this.initGUI();
		}

		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitubMock.this.mFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				TwitubMock.this.mFrame.setVisible(true);

				TwitubMock.this.mFrame.pack();
			}
		});
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		// Création de la fenetre principale
		mFrame = new JFrame("MOCK");
		mFrame.setLayout(new GridBagLayout());

		//
		// Gestion de la base de données

		JLabel dbLabel = new JLabel("Database");

		Button addUserButton = new Button("Add User");
		addUserButton.setPreferredSize(new Dimension(100, 50));
		addUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TwitubMock.this.addUserInDatabase();
			}
		});

		Button addTwitButton = new Button("Add Twit");
		addTwitButton.setPreferredSize(new Dimension(100, 50));
		addTwitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TwitubMock.this.addTwitInDatabase();
			}
		});

		//
		// Gestion des fichiers

		JLabel fileLabel = new JLabel("Files");

		Button sendUserButton = new Button("Send User");
		sendUserButton.setPreferredSize(new Dimension(100, 50));
		sendUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TwitubMock.this.sendUser();
			}
		});

		Button sendTwitButton = new Button("Send Twit");
		sendTwitButton.setPreferredSize(new Dimension(100, 50));
		sendTwitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TwitubMock.this.sendTwit();
			}
		});

		//
		// Ajout des composants à la fenêtre
		this.mFrame.add(dbLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		this.mFrame.add(addUserButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		this.mFrame.add(addTwitButton, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		this.mFrame.add(fileLabel, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(15, 5, 0, 5), 0, 0));
		this.mFrame.add(sendUserButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		this.mFrame.add(sendTwitButton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

	}

	/**
	 * Ajoute un utilisateur fictif à la base de donnée.
	 */
	protected void addUserInDatabase() {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser();

		// Ajout de l'utilisateur à la base
		this.mDatabase.addUser(newUser);
	}

	/**
	 * Génération et envoi d'un fichier utilisateur
	 */
	protected void sendUser() {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser();

		// Génération du fichier utilisateur
		this.mEntityManager.sendUser(newUser);
	}

	/**
	 * Génération d'un utilisateur fictif.
	 */
	protected User generateUser() {
		int randomInt = new Random().nextInt(99999);
		String userName = "MockUser" + randomInt;
		HashSet<String> follows =new HashSet<>();
		follows.add("toto");
		follows.add("titi");
		User newUser = new User(UUID.randomUUID(), userName, "--", userName, follows, "");
		return newUser;
	}

	/**
	 * Ajoute un twit fictif à la base de données.
	 */
	protected void addTwitInDatabase() {
		// Création 'un twit fictif
		Twit newTwit = this.generateTwit();

		// Ajout du twit
		this.mDatabase.addTwit(newTwit);
	}

	/**
	 * Génération et envoi d'un fichier twit
	 */
	protected void sendTwit() {
		// Création d'un twit fictif
		Twit newTwit = this.generateTwit();

		// Génération du fichier twit
		this.mEntityManager.sendTwit(newTwit);
	}

	/**
	 * Génération d'un twit fictif.
	 */
	protected Twit generateTwit() {
		// Si la base n'a pas d'utilisateur
		if (this.mDatabase.getUsers().size() == 0) {
			// Création d'un utilisateur
			this.addUserInDatabase();
		}

		// Récupération d'un utilisateur au hazard
		int userIndex = new Random().nextInt(this.mDatabase.getUsers().size());
		User randomUser = new ArrayList<User>(this.mDatabase.getUsers()).get(Math.max(0, userIndex - 1));

		// Création d'un twit fictif
		Twit newTwit = new Twit(randomUser, "Twit fictif!! #Mock #test ;)");

		return newTwit;
	}
}
