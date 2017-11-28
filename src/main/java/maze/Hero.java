package maze;

import java.util.Random;

import maze.Room;

class Hero extends Thread

{
	Room currentRoom;
	String name;
	int id;
	Scheduler scheduler;
	Maze maze;
	
	Hero(int id,String name, Room room,Maze maze,Scheduler scheduler)
	{
		this.id=id;
		this.currentRoom = room;
		this.name = name;
		this.scheduler=scheduler;
		this.maze=maze;
		
	}

	public void run()
	{
		try {
			while (true) {
				//boolean getTreasure=currentRoom.loot(id,name,scheduler);
				boolean getTreasure=maze.loot2(this,scheduler);
				if(getTreasure)
					break;
				sleep(1000);
			}
		} catch (InterruptedException e) {

		}
	}

	

}
