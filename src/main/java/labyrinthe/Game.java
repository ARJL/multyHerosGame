package labyrinthe;



public class Game {

	public static void main(String args[]) throws InterruptedException
	{
//**********************	Create the maze
		Maze maze =new Maze();
		
		System.out.println("adding rooms into the maze ...");
		maze.addRooms(100000);
		
		System.out.println("connecting rooms ...");
		maze.createMaze();

		
//**********************	Start the game
		Thread tab[]=new Thread[100];
		
		System.out.println("starting heros ...");
		for(int i=0;i<tab.length;i++){
			Hero h=new Hero(i,"Hero "+(i+1),maze.vertices.get(0));
			tab[i]=h;
		}
		for(int i=0;i<tab.length;i++){
			tab[i].start();
		}
	}

}
