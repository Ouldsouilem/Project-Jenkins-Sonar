package main.java.com.ubo.tp.twitub.controller;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.core.Twitub;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.datamodel.converter.XmlbeanDatamodelConverter;
import main.java.com.ubo.tp.twitub.datamodel.jaxb.JaxbWriter;
import main.java.com.ubo.tp.twitub.datamodel.jaxb.bean.user.UserXml;
import main.java.com.ubo.tp.twitub.ihm.ButtonEditor;
import main.java.com.ubo.tp.twitub.ihm.ButtonRenderer;
import main.java.com.ubo.tp.twitub.ihm.DisplayFileExample;
import main.java.com.ubo.tp.twitub.ihm.TwitubMock;


public class UserController {
	
	Database db;
	JFrame f;
	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


	public HashSet<File> getUserfiles() {
		return userfiles;
	}

	public void setUserfiles(HashSet<File> userfiles) {
		this.userfiles = userfiles;
	}


	User res;
	EntityManager em;
	HashSet<File> userfiles= new HashSet<>();
	
	public UserController(Database db,JFrame f) {
		this.db=db;
		this.f=f;
		this.em=new EntityManager(db);
		this.res=null;
		this.em.setExchangeDirectory("C:\\Users\\hould\\eclipse-workspace\\projet-meta.zip_expanded\\projet-ihm\\src\\main\\resources\\users");
	}
	
	public void verifyTagAndName(String tag,String name) {
		if(name.isEmpty() || tag.isEmpty()) {
      	  JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public boolean matchesAnotherUserTag(String tag) {
		return db.matchesAnotherUserTag(tag);
	}
	
	public void createUser(User u) {
		
		
		em.sendUser(u);
		db.addUser(u);
	}

	public Set<User> getUsers(){
		String path = "C:\\Users\\hould\\eclipse-workspace\\projet-meta.zip_expanded\\projet-ihm\\src\\main\\resources\\users";  
		// creating a file object  
		File fObj = new File(path);  
		// creating on object of the class DisplayFileExample  
		DisplayFileExample obj = new DisplayFileExample();
		
		
		if(fObj.exists() && fObj.isDirectory())  
		{  
		// array for the files of the directory pointed by fObj  
		File a[] = fObj.listFiles();  
		// display statements  
		System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");  
		System.out.println("Displaying Files from the directory : " + fObj);  
		System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		
		obj.printFileNames(a, 0, 0,userfiles);
		//System.out.println(toto);
		}
		Set<User> users = em.extractAllUsers(userfiles);
		return users;
	}
	
	
	public User verifyConnexion(String nom, String password) {
		
		Set<User> users = this.getUsers();
		for(User u : users) {
			this.db.addUser(u);
			if(u.getName().equals(nom)) {
				res = u;
			}
		}
		return res;
		
	}
}
	
  


