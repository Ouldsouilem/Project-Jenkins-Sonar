package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class HomeView extends JComponent{

	public HomeView(ProfileView profileView, User user, Set<Twit> twits) {
       JPanel panel1 = new JPanel(new GridBagLayout());
   
       panel1.setBackground(new Color(212, 236, 222));
 
	   ImageIcon icon= new ImageIcon(user.getAvatarPath());
	   Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
	   ImageIcon scaledIcon = new ImageIcon(image);
	   JLabel avatarLabel = new JLabel(scaledIcon);
	   panel1.add(avatarLabel, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	   
       JLabel nameLabel = new JLabel("  " + user.getName());
	   nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
	   nameLabel.setForeground(new Color(44, 62, 80)); 
	   panel1.add(nameLabel, new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	   
	   JLabel tagLabel = new JLabel("  " + user.getUserTag());
	   tagLabel.setFont(new Font("Arial", Font.ITALIC, 16));
	   tagLabel.setForeground(new Color(44, 62, 80)); 
	   panel1.add(tagLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    

	   
	   JPanel panel2 = new JPanel(new GridBagLayout());
	   panel2.setBackground(new Color(212, 236, 222));

	   JLabel twitsLabel = new JLabel("Twits");
	   twitsLabel.setFont(new Font("Arial", Font.BOLD, 16));
	   twitsLabel.setForeground(new Color(44, 62, 80));

	   // Ajouter le titreTwitsLabel en haut du panel2
	   GridBagConstraints c = new GridBagConstraints();
	   c.gridx = 0;
	   c.gridy = 0;
	   c.anchor = GridBagConstraints.NORTHWEST;
	   c.insets = new Insets(10, 10, 0, 0); // Espacement des bords du panel2
	   panel2.add(twitsLabel, c);

	   // Ajouter chaque twitLabel dans un tableau en dessous du titreTwitsLabel
	   c = new GridBagConstraints();
	   c.gridx = 0;
	   c.gridy = 1;
	   c.anchor = GridBagConstraints.NORTHWEST;
	   c.insets = new Insets(10, 10, 10, 10); // Espacement des bords du tableau
	   c.weighty = 1; // Faire en sorte que le tableau s'étende verticalement
	   c.fill = GridBagConstraints.HORIZONTAL;
	   JPanel twitPanel = new JPanel(new GridLayout(0, 1, 0, 10)); 
	
	   // Un tableau avec une colonne et un espacement vertical de 10px entre les éléments
	   twitPanel.setBackground(new Color(255, 255, 255));
	   // Couleur de fond du tableau 
	   twitPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(211, 211, 211)));
	   
	   for(Twit t : twits) {
	       JLabel UsertwitLabel = new JLabel("@" + t.getTwiter().getName());
	       UsertwitLabel.setFont(new Font("Arial", Font.ITALIC, 16)); 
	       UsertwitLabel.setForeground(new Color(44, 62, 80));
	       twitPanel.add(UsertwitLabel);
	       JLabel twitLabel = new JLabel(" " + t.getText());
	       twitLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
	       twitLabel.setForeground(new Color(44, 62, 80));     
	       twitLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
	       twitPanel.add(twitLabel);
	      
	   }
	   panel2.add(twitPanel, c);
	   
	   JPanel panel3 = new JPanel(new GridBagLayout());
	   panel3.setBackground(new Color(212, 236, 222));
	   String followes = user.getFollowsString();
	   if(followes.length() > 0) {
		   JLabel followsLabel = new JLabel("Follows " + user.getFollowsString());
		   followsLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
		   followsLabel.setForeground(new Color(44, 62, 80)); 
		   panel3.add(followsLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));		   
	   }

	   
	   //profileView.setLayout();
		profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileView.add(panel1,BorderLayout.NORTH);
		profileView.add(panel2,BorderLayout.CENTER);
		profileView.add(panel3,BorderLayout.WEST);
		profileView.setVisible(true);
	}
}
