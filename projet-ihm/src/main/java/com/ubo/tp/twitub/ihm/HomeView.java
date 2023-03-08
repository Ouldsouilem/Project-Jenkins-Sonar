package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class HomeView extends JComponent{

	public HomeView(ProfileView profileView, User user, Set<Twit> twits) {
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
       panel.setBackground(new Color(212, 236, 222));
       
       JLabel nameLabel = new JLabel("      " + user.getName());
	   nameLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Police et taille
	   nameLabel.setForeground(new Color(44, 62, 80)); // Couleur de texte
	   panel.add(nameLabel);
    
		// Label Avatar
	   ImageIcon icon= new ImageIcon(user.getAvatarPath());
	   Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Taille de l'image
	   ImageIcon scaledIcon = new ImageIcon(image);
	   JLabel avatarLabel = new JLabel(scaledIcon);
	   avatarLabel.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 3)); // Bordure
	   panel.add(avatarLabel);
	   
	   JLabel tagLabel = new JLabel("" + user.getUserTag());
	   tagLabel.setFont(new Font("Arial", Font.ITALIC, 18));
	   // Police et taille
	   tagLabel.setForeground(new Color(44, 62, 80)); // Couleur de texte
	   panel.add(tagLabel);
	
	   // Label Follows
	   JLabel followsLabel = new JLabel("Follows: " + user.getFollows());
	   followsLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
	   // Police et taille
	   followsLabel.setForeground(new Color(44, 62, 80)); 
	   // Couleur de texte
	   panel.add(followsLabel);
	
	    
/*	   // Label Twit
	   JLabel twitsLabel = new JLabel("Twits:");
	   twitsLabel.setFont(new Font("Arial", Font.BOLD, 20));
	   // Police et taille
	   twitsLabel.setForeground(new Color(44, 62, 80)); 
	   // Couleur de texte
	   panel.add(twitsLabel);
	   
	// Labels des twits
	   for(Twit t : twits) {
	   JLabel twitLabel = new JLabel("- " + t.getText());
	   twitLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
	   // Police et taille
	   twitLabel.setForeground(new Color(44, 62, 80));
	   // Couleur de texte
	       panel.add(twitLabel);
	   }      
	       
	*/


        
		profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileView.add(panel, BorderLayout.CENTER);
		profileView.setVisible(true);
	}
}
