/**
 * 
 */
package main.java.com.ubo.tp.twitub.ihm;

import java.io.File;
import java.util.HashSet;

/**
 * @author hould
 *
 */
public class DisplayFileExample {
	public void printFileNames(File[] a, int i, int lvl,HashSet<File> toto)  
	{  
	// base case of the recursion  
	// i == a.length means the directory has   
	// no more files. Hence, the recursion has to stop  
	if(i == a.length){  
	return;  
	}  
	// checking if the encountered object is a file or not  
	if(a[i].isFile())  {  
	System.out.println(a[i].getName());  
	toto.add(a[i]);
	}  
	// recursively printing files from the directory  
	// i + 1 means look for the next file  
	printFileNames(a, i + 1, lvl,toto);  
}
}
