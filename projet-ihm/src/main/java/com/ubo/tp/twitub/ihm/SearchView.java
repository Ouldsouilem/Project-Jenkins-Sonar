package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;

import java.awt.Color;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class SearchView extends JComponent{
	User res;
	Database db;
	HashSet<File> userfiles= new HashSet<>();
	EntityManager em;
	
	public SearchView(UserController controller, User res2, Database database, HashSet<File> hashSet, EntityManager entityManager, ProfileView profileView) {
		JTextField searchField = new JTextField(20);
		JButton searchButton = new JButton("Search");
		Set<User> results = new HashSet<>();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Tag");
		model.addColumn("Abonner");
		model.addColumn("Désabonner");
		
		JTable table = new JTable(model);
		TableColumn suivreColumn = table.getColumnModel().getColumn(2);
		TableColumn desabonnerColumn = table.getColumnModel().getColumn(3);
		suivreColumn.setCellRenderer(new ButtonRenderer());
		desabonnerColumn.setCellRenderer(new ButtonRenderer());
		suivreColumn.setCellEditor(new ButtonEditor(new JCheckBox(),res2,database,entityManager,profileView));
		desabonnerColumn.setCellEditor(new ButtonEditor(new JCheckBox(),res2,database,entityManager,profileView));
		System.out.println(res2.getName());
		for (User user : controller.getUsers()) {
			if(!user.getName().equals(res2.getName())) {
		    model.addRow(new Object[]{user.getName(), user.getUserTag(),"Abonner","Désabonner"});
			}

		}

		searchField.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    for (User user : controller.getUsers()) {
			    //	if(!user.getName().equals(res2.getName())) {
			        if (user.getName().toLowerCase().contains(searchTerm)) {
			            results.add(user);
			        }
			   // 	}
			    }
			    // Clear the JTable model before adding the search results
			    model.setRowCount(0);
			    for (User user : results) {
			    	if(!user.getName().equals(res2.getName())) {
			        model.addRow(new Object[]{user.getName(), user.getUserTag(),"Suivre","Désabonner"});
			    	}else {
			    	model.addRow(new Object[]{user.getName(), user.getUserTag(),"",""});
			    	}
			    }
		    }

		   @Override
		    public void removeUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    for (User user : controller.getUsers()) {
			        if (user.getName().toLowerCase().contains(searchTerm)) {
			            results.add(user);
			    	}
			    }
			    model.setRowCount(0);
			    for (User user : results) {
			    	if(!user.getName().equals(res2.getName())) {
			        model.addRow(new Object[]{user.getName(), user.getUserTag(),"Suivre","Désabonner"});
			    }else {
			    	model.addRow(new Object[]{user.getName(), user.getUserTag(),"",""});
			    	}
			    }
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	String searchTerm = searchField.getText().toLowerCase();
			    results.clear();
			    for (User user : controller.getUsers()) {
			        if (user.getName().toLowerCase().contains(searchTerm)) {
			            results.add(user);
			    	}
			    }
			    // Clear the JTable model before adding the search results
			    model.setRowCount(0);
			    for (User user : results) {
			    	if(!user.getName().equals(res2.getName())) {
			        model.addRow(new Object[]{user.getName(), user.getUserTag(),"Suivre","Désabonner"});
			    }else {
			    	model.addRow(new Object[]{user.getName(), user.getUserTag(),"",""});
			    	}
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
		profileView.setVisible(true);

	}

}
