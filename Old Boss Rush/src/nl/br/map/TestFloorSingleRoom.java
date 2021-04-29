package nl.br.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import en.lib.io.IO;
import nl.br.panels.DrawPanel;
import nl.br.props.Prop;

public class TestFloorSingleRoom extends Floor {
	
	public TestFloorSingleRoom() {
		super();
	}
	
	public void stitchRooms() {
		spawnRoom = new Room(0, 0, "resources/rooms/Miscellaneous/Spawn");
		rooms.add(spawnRoom);
	}
}
