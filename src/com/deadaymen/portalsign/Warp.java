package com.deadaymen.portalsign;

import java.io.Serializable;
import cn.nukkit.level.Location;

/**
 * author: DeaDAymen(0669)
 * SignPortal 
 */

public class Warp implements  Serializable{
//	private static final transient long serialVersionUID = 1L;
	private transient String name;
	public double x;
	public double y;
	public double z;
	
	public Warp(Location loc,String _name) {
		this.x = loc.x;
		this.y = loc.y;
		this.z = loc.z;
		this.name = _name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public Location getLocation(){
		return new Location(x,y,z);
	}

}
