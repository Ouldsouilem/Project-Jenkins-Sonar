package main.java.com.ubo.tp.twitub.controller;

import java.io.File;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.DisplayFileExample;

public class TwitController {

	Database db;
	JFrame f;
	Twit res;
	EntityManager em;
	HashSet<File> twitfiles= new HashSet<>();
	
	public TwitController(Database db,JFrame f) {
		this.db=db;
		this.f=f;
		this.em=new EntityManager(db);
		this.res=null;
		this.em.setExchangeDirectory("C:\\Users\\hould\\eclipse-workspace\\projet-meta.zip_expanded\\projet-ihm\\src\\main\\resources\\twits");
	}
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


	public HashSet<File> getTwitsfiles() {
		return twitfiles;
	}

	public void setTwitfiles(HashSet<File> twitfiles) {
		this.twitfiles = twitfiles;
	}
	

	public void createTwit(Twit t) {
		System.out.println(t);
		em.sendTwit(t);
		db.addTwit(t);
		
	}

	public Set<Twit> getTwits(){
		String path = "C:\\Users\\hould\\eclipse-workspace\\projet-meta.zip_expanded\\projet-ihm\\src\\main\\resources\\twits";  
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
		
		obj.printFileNames(a, 0, 0,twitfiles);
		//System.out.println(toto);
		}
		Set<Twit> twits = em.extractAllTwits(twitfiles);
		return twits;
	}
	
		
	
	
	
	
}