package game;

import java.util.Scanner;
import java.util.Vector;

class Room extends Thread {

    private Vector<Integer> ids = new Vector<Integer>();
    int treasure=0;
    boolean gameOver=false;
   
    public void run() {
        try {
            while ( true ) {
                putHeroTurn();
                sleep( 1000 );
            }
        } 
        catch( InterruptedException e ) { }
    }

    synchronized void putHeroTurn() throws InterruptedException
    {
        //while ( ids.size() == MAXids  )
    	while(ids.size()>0)
    		wait();
        //ids.addElement( new java.util.Date().toString());
        ids.addElement(1);
        ids.addElement(2);
        ids.addElement(3);
        notify();
    }
    
    // Called by Consumer
    public synchronized boolean loot(int heroId,String heroName)throws InterruptedException
    {
        notify();
        while ( ids.size() == 0 )
        	wait();
    	int id = (int)ids.firstElement();
    	if((heroId==id)  && (!gameOver)){
    		ids.removeElement( id );
    		System.out.print(heroName+" this is your turn so plz enter an int ");
    		Scanner scan = new Scanner(System.in);
    		int i=scan.nextInt();
    		incrementTreasure(i);
    		if ((treasure>=10)){
    			System.out.println(heroName+" you are the winner");
    			System.out.println("GAME OVER ");
    			gameOver=true;
    			return gameOver;
    		}
    		return gameOver;
    	}
    	return gameOver;
                
    }
    
    void incrementTreasure(int i){
    	treasure+=i;
    	System.out.println("treasure = "+treasure);
    	System.out.println();
    }

} 
