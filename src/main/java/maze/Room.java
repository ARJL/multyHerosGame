package maze;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

class Room extends Thread  {
	
	//***************
	
	//**************

    int treasure=0;
    
    int idRoom;
	LinkedList<Room> adj;
	
	
	public Room(){
		
	}
	
	public Room(int id,int treasure){
		this.idRoom=id;
		this.treasure=treasure;
		this.adj=new LinkedList<Room>();
	}
	
	public void displayAdjacentRooms( ){
		for(Room neighbour: this.adj){
			System.out.print(neighbour.idRoom+"  ");
		}
	}
	
	
   
	void incrementTreasure(int i){
    	treasure+=i;
    	System.out.println("treasure = "+treasure);
    	System.out.println();
    }
	
//    public synchronized boolean loot(int heroId,String heroName,Scheduler scheduler)throws InterruptedException
//    {
//        notify();
//        while ( scheduler.ids.size() == 0 ){
//        	wait();
//        }
//    	int id = (int)scheduler.ids.firstElement();
//    	if((heroId==id)  && (!scheduler.gameOver)){
//    		scheduler.ids.removeElement( id );
//    		System.out.print(heroName+" this is your turn so plz enter an int ");
//    		Scanner scan = new Scanner(System.in);
//    		int i=scan.nextInt();
//    		incrementTreasure(i);
//    		if ((treasure>=10)){
//    			System.out.println(heroName+" you are the winner");
//    			System.out.println("GAME OVER ");
//    			scheduler.gameOver=true;
//    			return scheduler.gameOver;
//    		}
//    		return scheduler.gameOver;
//    	}
//    	return scheduler.gameOver;
//                
//    }
    
    
    

} 

