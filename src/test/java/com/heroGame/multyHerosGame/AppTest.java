package com.heroGame.multyHerosGame;



import org.infinispan.creson.Factory;
import org.junit.Before;
import org.junit.Test;

//*******************
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.infinispan.protostream.FileDescriptorSource;
//import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

//*******************


import labyrinthe.Hero;
import labyrinthe.Maze;
import labyrinthe.Room;
import labyrinthe.RoomMarsheller;


public class AppTest {
	
	private String readResource(String resourcePath) throws IOException {
	      try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
	         Reader reader = new InputStreamReader(is, "UTF-8");
	         StringWriter writer = new StringWriter();
	         char[] buf = new char[1024];
	         int len;
	         while ((len = reader.read(buf)) != -1) {
	            writer.write(buf, 0, len);
	         }
	         return writer.toString();
	      }
	   }
	
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
    public void should_handle_request() throws IOException {
    	

		
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
		
		//***************************** 
				ConfigurationBuilder clientBuilder = new ConfigurationBuilder();
				clientBuilder.addServer().host("localhost").port(11222).marshaller(new ProtoStreamMarshaller());

				RemoteCacheManager remoteCacheManager = new RemoteCacheManager(clientBuilder.build());

				SerializationContext serCtx = ProtoStreamMarshaller.getSerializationContext(remoteCacheManager);

				FileDescriptorSource fds = new FileDescriptorSource();
				fds.addProtoFiles("/library.proto");
				serCtx.registerProtoFiles(fds);
				serCtx.registerMarshaller(new RoomMarsheller());
				//****************************
				
				//**************
				
				RemoteCache<Integer, Room> remoteCache = remoteCacheManager.getCache();
				if (remoteCache == null) {
			         System.out.println("Cache '"  + "' not found. Please make sure the server is properly configured");
			    }else{
			    	System.out.println("the cache used is: "+remoteCache);
			    }
				
			      // Register entity marshallers on the client side ProtoStreamMarshaller instance associated with the remote cache manager.
				//SerializationContext ctx = ProtoStreamMarshaller.getSerializationContext(remoteCacheManager);
				serCtx.registerProtoFiles(FileDescriptorSource.fromResources("/library.proto"));
				serCtx.registerMarshaller(new RoomMarsheller());
				
			      // generate the 'room.proto' schema file based on the annotations on Memo class and register it with the SerializationContext of the client
				 ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
			      String roomSchemaFile = protoSchemaBuilder
			            .fileName("room.proto")
			            .addClass(Room.class)
			            .build(serCtx);
				
			   // register the schemas with the server too
			      RemoteCache<String, String> metadataCache = remoteCacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
			      metadataCache.put("/library.proto", readResource("/library.proto"));
			      metadataCache.put("room.proto", roomSchemaFile);
			      String errors = metadataCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
			      if (errors != null) {
			         throw new IllegalStateException("Some Protobuf schema files contain errors:\n" + errors);
			      }
			      
			      
			      QueryFactory qf = Search.getQueryFactory(remoteCache);
			      Query query = qf.from(Room.class)
			            //.having("name").like("room1").toBuilder()
			            .build();
			      
			      System.out.println("***********query: ");
			      System.out.println(query);
			      System.out.println("***********query.getClass(): ");
			      System.out.println(query.getClass());
			      
			      List<Room> results = query.list();
//			      System.out.printf("Found %d matches:\n", results.size());
//			      
//			      for (Room room : results) {
//			         System.out.println(">> " + room);
//			      }
				//**************
		
    }

}
