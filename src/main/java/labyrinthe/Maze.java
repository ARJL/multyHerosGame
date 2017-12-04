package labyrinthe;

import java.util.ArrayList;
import java.util.Random;
import org.infinispan.creson.Shared;


public class Maze {
	
	@Shared public ArrayList<Room> vertices=new ArrayList<>();
	
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
	
	public void addRooms(int n){
		for(int i=0;i<n;i++){
			Room rm=new Room(i,1);
			vertices.add(rm);
		}
	}
	
	public void createMaze(){
		
		for(int i=0;i<this.vertices.size();i++){
			this.addUndirectedEdge(this.vertices.get(i), this.vertices.get(randomRoom(0, this.vertices.size())));
			this.addUndirectedEdge(this.vertices.get(i), this.vertices.get(randomRoom(0, this.vertices.size())));
			this.addUndirectedEdge(this.vertices.get(i), this.vertices.get(randomRoom(0, this.vertices.size())));
		}
	}
	
	private static int randomRoom(int lowerBound, int upperBound) {
	    Random r = new Random();
	    return r.nextInt(upperBound - lowerBound) + lowerBound;
	}
	
	
	
		
}

