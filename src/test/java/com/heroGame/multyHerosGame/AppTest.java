package com.heroGame.multyHerosGame;



import org.infinispan.creson.Factory;
import org.junit.Before;
import org.junit.Test;

import labyrinthe.Hero;
import labyrinthe.Maze;


public class AppTest {
	
	Maze maze;

    @Before
    public void setUp() throws Exception {
        Factory.get("localhost:11222"); // Creson initialization
        
        maze =new Maze();
		
		System.out.println("adding rooms into the maze ...");
		maze.addRooms(10);
		
		System.out.println("connecting rooms ...");
		maze.createMaze();
    }

    @Test
    public void should_handle_request() {
    	

		
//**********************	Start the game
		
		System.out.println("starting heros ...");
		Hero h1=new Hero(1,"SuperMario ",maze.vertices.get(0));
		Hero h2=new Hero(2,"SpiderMan ",maze.vertices.get(5));

		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
