package maze;

import java.util.Vector;

import maze.Hero;
import maze.Room;

public class Game {

	public static void main(String args[]) throws InterruptedException
	{
//**********************	Create the maze
		Maze maze =new Maze();
		Room room0=maze.addRoom(0,0);
		Room room1=maze.addRoom(1,0);
		Room room2=maze.addRoom(2,0);
		Room room3=maze.addRoom(3,0);
		Room room4=maze.addRoom(4,1);
		
		maze.addDirectedEdge(room0, room1);
		maze.addDirectedEdge(room0, room2);
		maze.addDirectedEdge(room1, room3);
		maze.addDirectedEdge(room2, room3);
		maze.addDirectedEdge(room2, room4);
		maze.addDirectedEdge(room3, room4);
		maze.addDirectedEdge(room3, room2);

		
//**********************	Start the game
				
		Scheduler scheduler = new Scheduler();
		scheduler.start();
		
		Hero h1=new Hero(1,"Mario", room0,maze,scheduler);
		Hero h2=new Hero(2,"Superman", room0,maze,scheduler);
		Hero h3=new Hero(3,"Ironman", room0,maze,scheduler);
		h1.start();
		h2.start();
		h3.start();
	}

}
