package com.deadaymen.portalsign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * author: DeaDAymen(0669)
 * SignPortal 
 */

public class Stream {
	
	Main plugin;
	
	public void Save(Warp w) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(plugin.getDataFolder() + File.separator + w.getName() + ".warp");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(w);
	         out.close();
	         fileOut.close();
	         plugin.getLogger().info("Warp have been saved on " + w.getName() + ".warp");
	      }catch(IOException i) {
	    	  plugin.getLogger().info("Warp didn't saved" + i.getMessage());
	         i.printStackTrace();
	      }
	}
	
	public Warp Load(String name) {
		try {
			 Warp wo;
	         FileInputStream fileIn = new FileInputStream(plugin.getDataFolder() + File.separator + name + ".warp");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         wo = (Warp) in.readObject();
	         in.close();
	         fileIn.close();
	         return wo;
	      }catch(IOException i) {
	         i.printStackTrace();
	        
	      }catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		return null;
	}
}
