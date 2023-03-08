package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import main.java.com.ubo.tp.twitub.controller.TwitController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class SearchTwitView extends JFrame {

	public SearchTwitView(TwitController controller, User res2, Database database, HashSet<File> twitfiles, EntityManager entityManager,
			ProfileView profileView) {
		JTextField searchField = new JTextField(20);
		JButton searchButton = new JButton("Search");
		Set<Twit> results = new HashSet<>();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Text");
		model.addColumn("User");
		
		JTable table = new JTable(model);
		
		System.out.println(res2.getName());
		
		Set<Twit> twits = controller.getEm().extractAllTwits(twitfiles);
		System.out.println("yoooooo"+twits);
		
		for (Twit twit : twits) {
			
		    model.addRow(new Object[]{twit.getText(), twit.getTwiter().getName()});
		}

		searchField.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    
			    for (Twit twit : twits) {
			    	System.out.println("User qui a tweete "+twit.getTwiter().getName());
			    	System.out.println("User connecte "+res2.getName());
			    	
			    	
			    	if (searchTerm.startsWith("@") && twit.getTwiter().getName().equals(res2.getName())) {
			    		results.add(twit);
			    	}else if(twit.getText().contains(res2.getName()) && !(searchTerm.startsWith("#"))) {
			            results.add(twit);
			    	}else if (searchTerm.equals("") || searchTerm.equals(" ")) {
			            results.add(twit);
			        }else if (searchTerm.startsWith("#") && twit.getText().contains(searchTerm)) {
			    		results.add(twit);
			        }
			  
			    }
			    // Clear the JTable model before adding the search results
			    model.setRowCount(0);
			    for (Twit twit : results) {
			    
			    	model.addRow(new Object[]{twit.getText(), twit.getTwiter().getName()});
			    	
			    	
			    }
		    }

		   @Override
		    public void removeUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    for (Twit twit : controller.getTwits()) {
			    	if (searchTerm.startsWith("@") && twit.getTwiter().getName().equals(res2.getName())) {
			    		results.add(twit);
			    	}else if(twit.getText().contains(res2.getName()) && !(searchTerm.startsWith("#"))) {
			            results.add(twit);
			    	}else if (searchTerm.equals("") || searchTerm.equals(" ")) {
			            results.add(twit);
			        }else if (searchTerm.startsWith("#") && twit.getText().contains(searchTerm)) {
			    		results.add(twit);
			        }
			  
			    }
			    // Clear the JTable model before adding the search results
			    model.setRowCount(0);
			    for (Twit twit : results) {
			    	 
			    	model.addRow(new Object[]{twit.getText(), twit.getTwiter().getName()});
			    	
			    }
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    for (Twit twit : controller.getTwits()) {
			    	if (searchTerm.startsWith("@") && twit.getTwiter().getName().equals(res2.getName())) {
			    		results.add(twit);
			    	}else if(twit.getText().contains(res2.getName()) && !(searchTerm.startsWith("#"))) {
			            results.add(twit);
			    	}else if (searchTerm.equals("") || searchTerm.equals(" ")) {
			            results.add(twit);
			        }else if (searchTerm.startsWith("#") && twit.getText().contains(searchTerm)) {
			    		results.add(twit);
			        }
		    }
		    // Clear the JTable model before adding the search results
		    model.setRowCount(0);
		    for (Twit twit : results) {
		    	
		    		model.addRow(new Object[]{twit.getText(), twit.getTwiter().getName()});
		    	
		    	
		    }
			    }
		    
		});

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.LIGHT_GRAY);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		JScrollPane tableScrollPane = new JScrollPane(table);
		profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileView.add(searchPanel, BorderLayout.NORTH);
		profileView.add(tableScrollPane, BorderLayout.CENTER);
		profileView.pack();
		profileView.setVisible(true);
	}

}