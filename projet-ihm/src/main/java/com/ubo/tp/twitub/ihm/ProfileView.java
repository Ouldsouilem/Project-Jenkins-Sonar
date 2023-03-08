package main.java.com.ubo.tp.twitub.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import main.java.com.ubo.tp.twitub.controller.TwitController;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class ProfileView extends JFrame {
	
	UserListView usl;
	SearchView svw;
	SendTwitView stv;
	SearchTwitView sstv;
	HomeView hv;
	User res;
	TwitController tw;


    public ProfileView(UserController controller, User res,TwitController tw) {
        super("My App");
        this.res = res;
        this.setSize(700,500);
        this.tw = tw;
        JMenuBar menuBar = new JMenuBar();
        JMenu profil = new JMenu("Profil");
        profil.setForeground(Color.WHITE);
        profil.setBackground(new Color(41, 128, 185));
        JMenu deconnexion = new JMenu("Deconnexion");
        deconnexion.setForeground(Color.WHITE);
        deconnexion.setBackground(new Color(41, 128, 185));
        JMenu twit = new JMenu("Twit");
        twit.setForeground(Color.WHITE);
        twit.setBackground(new Color(41, 128, 185));

        JMenuItem menuItem = new JMenuItem("Deconnexion");
        menuItem.addActionListener(e -> System.exit(0));
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(new Color(41, 128, 185));

        JMenuItem home = new JMenuItem("Home");
        home.setForeground(Color.WHITE);
        home.setBackground(new Color(41, 128, 185));
        JMenuItem ListeU = new JMenuItem("Liste Utilisateurs");
        ListeU.setForeground(Color.WHITE);
        ListeU.setBackground(new Color(41, 128, 185));
        JMenuItem search = new JMenuItem("Search");
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(41, 128, 185));
        JMenuItem sendTwit = new JMenuItem("Send twit");
        sendTwit.setForeground(Color.WHITE);
        sendTwit.setBackground(new Color(41, 128, 185));
        JMenuItem searchtwit = new JMenuItem("Search twit");
        searchtwit.setForeground(Color.WHITE);
        searchtwit.setBackground(new Color(41, 128, 185));
        twit.add(sendTwit);
        twit.add(searchtwit);
        profil.add(home);
        profil.add(ListeU);
        profil.add(search);
        deconnexion.add(menuItem);

        menuBar.add(profil);
        menuBar.add(deconnexion);
        menuBar.add(twit);
        menuBar.setForeground(Color.WHITE);
        menuBar.setBackground(new Color(41, 128, 185));

        setJMenuBar(menuBar);

        ListeU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				usl=new UserListView(controller.getUsers(),ProfileView.this);
				usl.setVisible(true);
			}
        });
        
        
        search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				svw=new SearchView(controller,res,controller.getDb(),controller.getUserfiles(),controller.getEm(),ProfileView.this);
				svw.setVisible(true);
			}
        });
        
         home.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getContentPane().removeAll();
			hv = new HomeView(ProfileView.this, res, tw.getTwits());
			hv.setVisible(true);
					
		}
    });       
        
        sendTwit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	getContentPane().removeAll();
                stv=new SendTwitView(controller,res,tw,ProfileView.this);
                stv.setVisible(true);
            }
        });
        
        searchtwit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                Set<Twit> twits = tw.getTwits();
                sstv=new SearchTwitView(tw,res,controller.getDb(),tw.getTwitsfiles(),tw.getEm(),ProfileView.this);
                sstv.setVisible(true);
            }
        });
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        setLocationRelativeTo(null);
        setVisible(true);
        
    
        setSize(700, 700);
       
    }

    public void openHomeVien() {
		getContentPane().removeAll();
		hv = new HomeView(ProfileView.this, res, tw.getTwits());
		hv.setVisible(true);
    }

    public void update(User usr) {
        String followsText = String.join(", ", usr.getFollows());
        JLabel follow = new JLabel();
        follow.setText("Follows: " +followsText);
        getContentPane().repaint();
    }
    
    
}
