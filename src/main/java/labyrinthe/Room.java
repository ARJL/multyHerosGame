package labyrinthe;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedList;


@Entity
public class Room  {

	@Id
    int treasure=0;
    
    int idRoom;
	LinkedList<Room> adj;

	public Room(){}

	public Room(int id,int treasure){
		//synchronized(this){
			this.idRoom=id;
			this.treasure=treasure;
			this.adj=new LinkedList<Room>();
		//}
		
	}

	public int loot(){
		int val=this.treasure;
		this.treasure=0;
		return val;
	}

}