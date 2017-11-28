package maze;

import java.util.Vector;

public class Scheduler extends Thread {

	public Vector<Integer> ids = new Vector<Integer>();
	boolean gameOver=false;
	
	public Scheduler(){
		
	}
	
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
    	while(ids.size()>0){
    		//System.out.println("size()>0");
    		Thread.sleep(1000);
    		//wait();
    	}
    	    	
        ids.addElement(1);
        ids.addElement(2);
        ids.addElement(3);
       notify();
    }
}
