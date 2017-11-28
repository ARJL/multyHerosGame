package maze;

import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	
	ArrayList<Room> vertices=new ArrayList<>();
	
	public Maze(){
		
	}
	
	public Room addRoom(int id,int treasure){
		Room rm=new Room(id,treasure);
		vertices.add(rm);
		return rm;
	}
	
	public void addDirectedEdge(Room start,Room end){
		start.adj.add(end);
	}
	
	public void addUndirectedEdge(Room start,Room end){
		addDirectedEdge(start,end);
		addDirectedEdge(end,start);
	}
	
	
	//****************************************************************************************
	
	public Room getRoomById(int id){
		for(Room room:vertices){
			if (room.idRoom==id)
				return room;
		}
		return null;
	}
	
	public synchronized boolean loot2(Hero hero,Scheduler scheduler)throws InterruptedException
    {
        notify();
        while ( scheduler.ids.size() == 0 ){
        	wait();
        }
    	int id = (int)scheduler.ids.firstElement();
    	if((hero.id==id)  && (!scheduler.gameOver)){
    		scheduler.ids.removeElement( id );
    		int i;
    		boolean choosenRoomIsReachable;
    		do{
    			System.out.print(hero.name+" this is your turn so plz choose a correct room id: ");
    			hero.currentRoom.displayAdjacentRooms();
        		Scanner scan = new Scanner(System.in);
        		i=scan.nextInt();
        		choosenRoomIsReachable=hero.currentRoom.adj.contains(getRoomById(i));
    		}while(!choosenRoomIsReachable);
    		hero.currentRoom=getRoomById(i);
    		
    		if ((hero.currentRoom.treasure==1)){
    			System.out.println(hero.name+" you are the winner");
    			System.out.println("GAME OVER ");
    			scheduler.gameOver=true;
    			return scheduler.gameOver;
    		}
    		
    		
    		return scheduler.gameOver;
    	}
    	return scheduler.gameOver;
                
    }
	
	
	
}

