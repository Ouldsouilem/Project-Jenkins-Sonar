package main.java.com.ubo.tp.twitub.ihm;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private String username;
    User usr;
    Database b;
    EntityManager em;
    HashSet<File> userfiles = new HashSet<File>();
    ProfileView pf;
    public ButtonEditor(JCheckBox checkBox,User res,Database b, EntityManager em, ProfileView pf) {
        super(checkBox);
        this.b=b;
        this.userfiles=userfiles;
        this.usr=res;
        this.pf=pf;
        this.em=em;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        username = table.getValueAt(row, 0).toString();
        return button;
    }
    
    public Object getCellEditorValue() {
        if (isPushed) {
        	if(button.getText().equals("Abonner")) {
	            User u = this.b.getUserByNom(username);
	            System.out.println(u);
	            this.usr.addFollowing(u.getUserTag());
	            String usermodified = this.em.getFileName(usr.getUuid(), "usr");
	            this.em.notifyModifiedFiles(userfiles);
	            System.out.println(u.getUserTag() + " is followed by "+this.usr.getName());
	            System.out.println("List of followers" + this.usr.getFollows());
	            System.out.println("User modified" + usermodified);
	            this.pf.update(this.usr);
	            this.em.sendUser(usr);
        	}else if(button.getText().equals("Désabonner")) {
        		User u = this.b.getUserByNom(username);
	            System.out.println(u);
	            this.usr.removeFollowing(u.getUserTag());
	            String usermodified = this.em.getFileName(usr.getUuid(), "usr");
	            this.em.notifyModifiedFiles(userfiles);
	            System.out.println(u.getUserTag() + " is unfollowed by "+this.usr.getName());
	            System.out.println("List of followers" + this.usr.getFollows());
	            System.out.println("User modified" + usermodified);
	            this.pf.update(this.usr);
	            this.em.sendUser(usr);
        	}
            }
        isPushed = false;
        return label;
    }
    
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
    
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}