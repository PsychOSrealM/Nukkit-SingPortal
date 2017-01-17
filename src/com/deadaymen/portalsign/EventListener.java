package com.deadaymen.portalsign;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSignPost;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

/**
 * author: DeaDAymen(0669)
 * SignPortal 
 */

public class EventListener implements Listener {
	Main plugin;
	Level world = null;
	Stream s;
	
    public EventListener(Main plugin) {
        this.plugin = plugin;
    }
    
	@EventHandler
	public void onSignChangeEvent(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[World]") && e.getPlayer().isOp()) {
			String wName = e.getLine(1);
			Level lv = plugin.getServer().getLevelByName(wName);
			
			if(lv != null){
				e.setLine(0, "§1[ World ]");
				e.setLine(1,  wName);
			}else {
				e.getPlayer().sendMessage(TextFormat.RED + "Can't find the world !");
			}
		}
		
		if(e.getLine(0).equalsIgnoreCase("[Warp]") && e.getPlayer().isOp()) {
			String wName = e.getLine(1);
			if(new File(plugin.getDataFolder() + File.separator + wName + ".warp").exists()){
				e.setLine(0, "§a[ Warp ]");
				e.setLine(1, wName);
			}else {
				e.getPlayer().sendMessage(TextFormat.RED + "Can't find the warp point !");
			}
		}
	 
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent Event) {
	  Player player = Event.getPlayer();
	  Block block = Event.getBlock();
	  
	  
	  if (Event.getAction() == PlayerInteractEvent.RIGHT_CLICK_AIR || Event.getAction() == PlayerInteractEvent.RIGHT_CLICK_BLOCK || Event.getAction() == PlayerInteractEvent.LEFT_CLICK_AIR || Event.getAction() == PlayerInteractEvent.LEFT_CLICK_BLOCK)
      {
		  //player.getPlayer().sendMessage(TextFormat.RED + "Clicked" + block.getName());
		  if (block instanceof BlockSignPost) {
			  BlockEntitySign sign = (BlockEntitySign) block.getLevel().getBlockEntity(block);
			  //player.getPlayer().sendMessage(TextFormat.RED + "Clickedd" + block.getName());
			  if (sign == null) {
			  return;
			  }
			  //player.getPlayer().sendMessage(TextFormat.RED + "Clickedddd" + block.getName());
		  	String line1 = sign.getText()[0];
		  	String line2 = sign.getText()[1];
            //String line3 = sign.getText()[2];
          	//String line4 = sign.getText()[3];
		  	player.getPlayer().sendMessage(TextFormat.RED + line2);
          	if(line1 == "§1[ World ]") {
          		world = plugin.getServer().getLevelByName(line2);
          		if(world == null) {
          			plugin.getLogger().info(TextFormat.RED + "Can't find the world");
          		}else {
          			player.sendMessage(TextFormat.GOLD + "Teleporting to " + TextFormat.GREEN + " " +line2);
          			player.teleport(world.getSafeSpawn());
          		}
          	}
          	if(line1 == "§a[ Warp ]") {
          		if(new File(plugin.getDataFolder() + File.separator + line2 + ".warp").exists()) {
          			
          			player.sendMessage(TextFormat.GOLD + "Teleporting to " + TextFormat.GREEN + " " +line2);
          			Warp wo = null;
          			try {
          		         FileInputStream fileIn = new FileInputStream(plugin.getDataFolder() + File.separator + line2 + ".warp");
          		         ObjectInputStream in = new ObjectInputStream(fileIn);
          		         wo = (Warp) in.readObject();
          		         in.close();
          		         fileIn.close();
          		      }catch(IOException i) {
          		         i.printStackTrace();
          		        
          		      }catch(ClassNotFoundException c) {
          		         
          		         c.printStackTrace();
          		      }
          			player.teleport(wo.getLocation());
          		}
          	}
		  }
      }
	}
}
	

