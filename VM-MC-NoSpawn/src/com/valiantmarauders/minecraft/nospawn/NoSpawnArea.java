package com.valiantmarauders.minecraft.nospawn;

import java.util.List;

import org.bukkit.Location;

import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.selection.Area;

public class NoSpawnArea extends Cuboid {

	public NoSpawnArea(List<Location> points) {
		// TODO Auto-generated constructor stub
		super(points);
	}

	@Override
	public Area clone() {
		// TODO Auto-generated method stub
		return new NoSpawnArea(points);
	}
}
