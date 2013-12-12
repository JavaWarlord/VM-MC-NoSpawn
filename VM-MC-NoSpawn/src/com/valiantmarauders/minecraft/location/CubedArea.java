package com.valiantmarauders.minecraft.location;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Defines the boundaries of a cube using points in opposite corners.
 * 
 * @author JavaWarlord
 * @version 0.1
 * 
 */
public class CubedArea {

	private World world;
	private int x1;
	private int y1;
	private int z1;
	private int x2;
	private int y2;
	private int z2;

	public CubedArea(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
		// TODO Auto-generated constructor stub
		this.world = world;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	public boolean containsLocation(Location location) {
		// TODO Auto-generated method stub
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		if ((x >= Math.min(x1, x2)) && (x <= Math.max(x1, x2))) {
			if ((y >= Math.min(y1, y2)) && (y <= Math.max(y1, y2))) {
				if ((z >= Math.min(z1, z2)) && (z <= Math.max(z1, z2))) {
					return true;
				}
			}
		}
		return false;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getZ1() {
		return z1;
	}

	public void setZ1(int z1) {
		this.z1 = z1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getZ2() {
		return z2;
	}

	public void setZ2(int z2) {
		this.z2 = z2;
	}
}
