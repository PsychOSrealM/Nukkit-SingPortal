package com.deadaymen.portalsign;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

/**
 * author: DeaDAymen(0669)
 * SignPortal 
 */

public class Main extends PluginBase {
	Stream s;
	
	@Override
	public void onLoad(){
		this.getLogger().info(TextFormat.GREEN + "Portal Sign " + this.getDescription().getVersion() + " By " + this.getDescription().getAuthors() + " Loadded!!!");
	}
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
		if(!(this.getDataFolder().exists())) {
			this.getLogger().info(TextFormat.GREEN + "Can't find the Data folder ..... Craeting one !");
			this.getDataFolder().mkdirs();
		}else {
			this.getLogger().info(TextFormat.GREEN + "Data folder founded !");
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage("This cmd can't excute on the console");
			return false;
		}else if (cmd.getName().equals("setwarp")) {
            saveWarp((Player) sender,args[0]);
        }

		
		return true;
	}
	
	public void saveWarp(Player player,String names) {
		Warp warp = new Warp(new Location(player.getX(),player.getY(),player.getZ()),names);
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(this.getDataFolder() + File.separator + warp.getName() + ".warp");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(warp);
	         out.close();
	         fileOut.close();
	         player.sendMessage(TextFormat.GREEN + "Warp have been saved on " + warp.getName());
	         this.getLogger().info("Warp have been saved on " + warp.getName() + ".warp");
	      }catch(IOException i) {
	    	 this.getLogger().info("Warp didn't saved" + i.getMessage());
	         i.printStackTrace();
	      }
	}
}
