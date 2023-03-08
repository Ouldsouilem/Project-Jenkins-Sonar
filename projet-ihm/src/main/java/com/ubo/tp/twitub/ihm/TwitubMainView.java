package main.java.com.ubo.tp.twitub.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.java.com.ubo.tp.twitub.controller.TwitController;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;


/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {
	public JFrame fr = new JFrame();
	Database db = new Database();
	UserController usc = new UserController(db,fr);
	TwitController tw = new TwitController(db,fr);
	

	
	  public TwitubMainView() {
			 fr.setSize(400, 300); // Définir la taille de la fenêtre
		     fr.setTitle("twItUB"); // Définir le titre de la fenêtre
		     fr.setVisible(true); // Rendre la fenêtre visible
		     fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     System.out.println(db.getUsers());
	         
		    JMenuBar bar = new JMenuBar();
		    fr.setJMenuBar(bar);
		   
		    
		    JMenu fichier = new JMenu("Fichier");
		    JMenu interog = new JMenu("?");	
		    JMenu load = new JMenu("Load File");	
		    JMenu compte = new JMenu("compte");
		    
		    bar.add(fichier);	
		    bar.add(interog);
		    bar.add(load);
		    bar.add(compte);
		    
		    JMenuItem fichierItem = new JMenuItem("Quitter");
		    fichier.add(fichierItem);
		    fichierItem.addActionListener(e -> System.exit(0));
		    ImageIcon icone = new ImageIcon("src/main/resources/images/exitIcon_20.png");
		    fichierItem.setIcon(icone); 
		 
		    JMenuItem interogItem = new JMenuItem("A propos");
		    interog.add(interogItem);
		    interogItem.addActionListener(e -> about());
		    
		    JMenuItem loadItem = new JMenuItem("Load file");
		    load.add(loadItem);
		    loadItem.addActionListener(e -> load());
		    
		    JMenuItem compteItem1 = new JMenuItem("Inscription");
		    compte.add(compteItem1);
		    compteItem1.addActionListener(e -> inscription());
		    
		    JMenuItem compteItem2 = new JMenuItem("Connexion");
		    compte.add(compteItem2);
		    compteItem2.addActionListener(e -> connexion());
	    
	    
	    }
  
	  public void about() {
		  
		  JLabel label = new JLabel("<html><b>UBO M2-TIIL</b> Département informatique</html>");
		  ImageIcon icon = new ImageIcon("src/main/resources/images/logo_50.jpg");
	 	  JOptionPane.showMessageDialog(null, label, "A propos", JOptionPane.INFORMATION_MESSAGE, icon);
	  }
	  
	  public void load() {
		  JFileChooser fileChooser = new JFileChooser();
		  int resultat = fileChooser.showOpenDialog(fr);
          if (resultat == JFileChooser.APPROVE_OPTION) {
              System.out.println("Le fichier sélectionné est : " + fileChooser.getSelectedFile().getName());
           }		  
		  
	  }
	  
	  public void inscription() {
	        JLabel labelNom = new JLabel("Nom :");
	        JTextField champNom = new JTextField(10);
	        JLabel labelPassword = new JLabel("Password :");
	        JPasswordField champPassword = new JPasswordField(10);
	        JLabel labelTag = new JLabel("Tag :");
	        JTextField champTag = new JTextField(10);
	        JLabel labelAvatar = new JLabel("Avatar :");
	        JTextField champAvatar = new JTextField(10);
	        JButton fileChooserButton = new JButton("Choose Avatar File");
	        
	        		  
	        
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        
	        panel.add(labelNom);
	        panel.add(champNom);
	        panel.add(labelPassword);
	        panel.add(champPassword);
	        panel.add(labelTag);
	        panel.add(champTag);
	        panel.add(labelAvatar);
	        panel.add(champAvatar);
	        panel.add(fileChooserButton);
	        
	        fileChooserButton.addActionListener(e -> {
	            JFileChooser fileChooser = new JFileChooser();
	            int result = fileChooser.showOpenDialog(fr);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                String filename = fileChooser.getSelectedFile().getPath();
	                champAvatar.setText(filename);
	            }
	        });
	        
			  int resultat = JOptionPane.showConfirmDialog(null, panel, "Inscription", JOptionPane.OK_CANCEL_OPTION);
	          if (resultat == JOptionPane.OK_OPTION) {
	              String nom = champNom.getText();
	              String tag = champTag.getText();
	              String avatar = champAvatar.getText();
	              String password=champPassword.getText();
	              
	              usc.verifyTagAndName(tag,nom);
	              if (usc.matchesAnotherUserTag(tag) == true) {
	                  JOptionPane.showMessageDialog(null, "Le tag entré existe déjà. Veuillez en choisir un autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
	              }
	              else {
	            	  int randomInt = new Random().nextInt(99999);
	          		  User newUser = new User(UUID.randomUUID(), tag, password, nom, new HashSet<>(), avatar);
	            	  usc.createUser(newUser);
	            	  System.out.println(newUser.toString());
	           
	              }
	             
	           }	        

	  }

	  
	  public void connexion() {
		    JFrame frame = new JFrame("Login");
		    frame.setPreferredSize(new Dimension(400, 300));

		    // Create and style title label
		    JLabel titleLabel = new JLabel("Connexion");
		    titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		    titleLabel.setForeground(Color.WHITE); // light cyan
		    titleLabel.setHorizontalAlignment(JLabel.CENTER);

		    // Create and style input fields
		    JPanel inputPanel = new JPanel();
		//		    inputPanel.setLayout(new GridLayout(0, 1, 10, 10));
		    inputPanel.setLayout(new GridBagLayout());
	//	    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		    inputPanel.setBackground(Color.WHITE); // ghost white
		    
		    JLabel labelNom = new JLabel("Login");
		    labelNom.setForeground(Color.decode("#385898"));
		    labelNom.setFont(new Font("Helvetica", Font.BOLD, 18));
		    JTextField champNom = new JTextField(10);
		    champNom.setBorder(BorderFactory.createCompoundBorder(
		            BorderFactory.createLineBorder(Color.decode("#385898"), 2),
		            BorderFactory.createEmptyBorder(5, 5, 5, 5)
		    ));
		    champNom.setFont(new Font("Helvetica", Font.PLAIN, 16));
		    champNom.setPreferredSize(new Dimension(30, 30));
		   
		    
		    
		    JLabel labelPassword = new JLabel("Password");
		    labelPassword.setForeground(Color.decode("#385898")); 
		    labelPassword.setFont(new Font("Helvetica", Font.BOLD, 18));
		    JPasswordField champPassword = new JPasswordField(10);
		    champPassword.setBorder(BorderFactory.createCompoundBorder(
		            BorderFactory.createLineBorder(Color.decode("#385898"), 2),
		            BorderFactory.createEmptyBorder(5, 5, 5, 5)
		    ));
		    champPassword.setFont(new Font("Helvetica", Font.PLAIN, 16));
		    champPassword.setPreferredSize(new Dimension(30, 30));

		    // Create and style buttons
		    JPanel buttonPanel = new JPanel();		    
		    buttonPanel.setBackground(Color.WHITE); // ghost white
		    JButton okButton = new JButton("Se connecter");
		    JButton cancelButton = new JButton("Annuler");
		    okButton.setBackground(Color.decode("#70C1B3"));
		    okButton.setForeground(Color.WHITE);
		    okButton.setFocusPainted(false);
		    okButton.setForeground(Color.decode("#E87E04")); // light cyan
		    cancelButton.setForeground(Color.WHITE);
		    cancelButton.setFocusPainted(false);
// dim gray

		    // Add components to panels
		//    inputPanel.add(labelNom);
		   inputPanel.add(labelNom, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));
	//	    inputPanel.add(champNom);
		    inputPanel.add(champNom, new GridBagConstraints(2, 0, 2, 1, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
		    inputPanel.add(labelPassword, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));
		    inputPanel.add(champPassword, new GridBagConstraints(2, 1, 2, 1, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
	//	    inputPanel.add(labelPassword);
	//	    inputPanel.add(champPassword);
		    buttonPanel.add(okButton);
		    buttonPanel.add(cancelButton);


		    // Add panels to frame
		    frame.getContentPane().setBackground(Color.decode("#34495E")); // light cyan
		    frame.getContentPane().add(titleLabel, BorderLayout.NORTH);
		    frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
		    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		    // Add action listeners to buttons
		    okButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            String nom = champNom.getText();
		            String password = champPassword.getText();
		            User res = usc.verifyConnexion(nom, password);
		            if (res == null || !res.getUserPassword().equals(password)) {
		                JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Connecté en tant que " + res.getName(), "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
		                ProfileView pf = new ProfileView(usc, res,tw);
		                frame.dispose();
		            }
		        }
		    });

		    cancelButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            frame.dispose();
		        }
		    });

		    // Set frame properties
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setLocationRelativeTo(null); // center frame on screen
		    frame.setVisible(true);
		}

	  }	
