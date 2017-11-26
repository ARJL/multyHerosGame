package game;


public class Game {

	
	public static void main(String args[]) throws InterruptedException
	{
		Room room = new Room();
		room.start();
		// Start two this time
		Hero h1=new Hero(1,"Mario", room);
		Hero h2=new Hero(2,"Superman", room);
		Hero h3=new Hero(3,"Ironman", room);
		h1.start();
		h2.start();
		h3.start();
	}

}
