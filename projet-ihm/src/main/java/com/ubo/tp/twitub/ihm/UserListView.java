package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class UserListView extends JComponent{
	private static final int IMAGE_SIZE = 70;
    public UserListView(Set<User> users,ProfileView profileView) {
    	JPanel panel = new JPanel();
    	
        for (User user : users) {
        	ImageIcon icon = new ImageIcon(user.getAvatarPath());
        	Image img = icon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        	ImageIcon newIcon = new ImageIcon(img);
        	JLabel label = new JLabel(user.getName(), newIcon, JLabel.CENTER);
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
		profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileView.add(scrollPane, BorderLayout.NORTH);
		profileView.add(scrollPane, BorderLayout.CENTER);
		profileView.setVisible(true);        

    }

}
