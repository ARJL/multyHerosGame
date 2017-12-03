package labyrinthe;

import java.util.LinkedList;


public class Room  {
	
    int treasure=0;
    
    int idRoom;
	LinkedList<Room> adj;
	
	public Room(int id,int treasure){
		synchronized(this){
			this.idRoom=id;
			this.treasure=treasure;
			this.adj=new LinkedList<Room>();
		}
		
	}

	public int loot(){
		int val=this.treasure;
		this.treasure=0;
		return val;
	}

}