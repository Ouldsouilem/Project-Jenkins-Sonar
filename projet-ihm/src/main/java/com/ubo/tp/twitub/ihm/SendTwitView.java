package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.java.com.ubo.tp.twitub.controller.TwitController;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class SendTwitView extends JComponent{

    JButton twitButton;
    
    public SendTwitView(UserController controller, User res, TwitController tw, ProfileView profileView) {
           JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JLabel label = new JLabel("Twit :");
           panel.add(label);
           JTextField champ = new JTextField(250);
           panel.add(champ);
           twitButton = new JButton("Publier");
           panel.add(twitButton);
           add(panel); 
           twitButton.addActionListener(e-> { if( ! (champ.getText().isEmpty())) {
               Twit t = new Twit(res,champ.getText());
               tw.createTwit(t);
               profileView.openHomeVien();
           }
           });

   		profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		profileView.add(panel, BorderLayout.NORTH);
   		profileView.add(panel, BorderLayout.CENTER);
   		profileView.setVisible(true);

    }

}
