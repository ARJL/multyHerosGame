package labyrinthe;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
//import org.infinispan.protostream.annotations.ProtoDoc;
//import org.infinispan.protostream.annotations.ProtoField;

import java.io.Serializable;
import java.util.LinkedList;

@Indexed
//@ProtoDoc("@Indexed")
@Entity
public class Room implements Serializable {

	@Field String name;
	//@ProtoField (number = 1, required = true) String name;
	
	@Id int idRoom;
	int treasure=0;
	LinkedList<Room> adj;
	
	public Room(){}

	public Room(int id,int treasure){
		//synchronized(this){
			this.name="room"+id;
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