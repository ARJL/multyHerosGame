package game;

class Hero extends Thread

{
	Room room;
	String name;
	int id;
	
	Hero(int id,String name, Room room)
	{
		this.id=id;
		this.room = room;
		this.name = name;
	}

	public void run()
	{
		try {
			while (true) {
				boolean getTreasure=room.loot(id,name);
				if(getTreasure)
					break;
				//System.out.println(name + " got treasure: " + treasure);
				sleep(1000);
			}
		} catch (InterruptedException e) {

		}
	}

	

}
