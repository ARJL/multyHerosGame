package labyrinthe;

import java.io.IOException;
import java.util.LinkedList;

import org.infinispan.protostream.MessageMarshaller;

public class RoomMarsheller implements MessageMarshaller<Room>{

	@Override
	public Class<? extends Room> getJavaClass() {
		// TODO Auto-generated method stub
		return Room.class;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Room";
	}

	@Override
	public Room readFrom(org.infinispan.protostream.MessageMarshaller.ProtoStreamReader reader) throws IOException {
		  String name = reader.readString("name");
	      int idRoom = reader.readInt("idRoom");
	      int treasure = reader.readInt("treasure");
	      LinkedList<Room> adj=reader.readCollection("adj", new LinkedList(), Room.class);
	     
	      Room room = new Room();
	      room.name=name;
	      room.idRoom=idRoom;
	      room.treasure=treasure;
	      room.adj=adj;
	      return room;
	}

	@Override
	public void writeTo(org.infinispan.protostream.MessageMarshaller.ProtoStreamWriter writer, Room room)
			throws IOException {
		   writer.writeString("name", room.name);
		   writer.writeInt("idRoom", room.idRoom);
		   writer.writeInt("treasure", room.treasure);
		   writer.writeCollection("adj", room.adj, Room.class);
		   
	}

}
