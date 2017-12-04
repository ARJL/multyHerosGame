package labyrinthe;

import java.util.HashSet;
import java.util.LinkedList;


public class Hero extends Thread

{
	Room startroom;
	String name;
	int id;
	
	public Hero(int id,String name, Room room)
	{
		this.id=id;
		this.startroom = room;
		this.name = name;
		
	}

	public int pickTreasures(Room start){
		//bfs and pick treasures
		int nbTreasuresPicked = 0;
		LinkedList<Room> queue=new LinkedList<>();
		HashSet<Room>visitedRooms=new HashSet<Room>();
		
		queue.add(start);
		while(!queue.isEmpty()){
			
			Room room=queue.remove();
			
			nbTreasuresPicked+=room.loot();
					
			visitedRooms.add(room);
			for(Room neighbour: room.adj){
				if(!visitedRooms.contains(neighbour)) 
					queue.add(neighbour);
			}
		}		
		
		return nbTreasuresPicked;
	}
	public void run()
	{
		System.out.println(this.name+" picked "+pickTreasures(startroom)+" treasures");	
		
	}

	

}

