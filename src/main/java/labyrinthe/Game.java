package labyrinthe;

public class Game {

	public static void main(String args[]) throws InterruptedException {
		// ********************** Create the maze
		Maze maze = new Maze();

		System.out.println("adding rooms into the maze ...");
		maze.addRooms(100000);

		System.out.println("connecting rooms ...");
		maze.createMaze();

		// ********************** Start the game

		System.out.println("starting heros ...");
		Hero h = new Hero(1, "SuperMario ", maze.vertices.get(0));

		h.start();
		try {
			h.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
