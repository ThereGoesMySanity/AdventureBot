package world;

import java.io.File;
import java.util.ArrayList;

import world.beings.Player;

public class World implements Runnable{
	static final File[] biomeFiles = 
		{
				new File("Resources/desert.bio"),
				new File("Resources/tundra.bio"),
				new File(""),
				new File(""),
				new File(""),
				new File(""),
				new File(""),
				new File(""),
		};
	ArrayList<Biome> biomes;
	ArrayList<Player> players;
	String name;
	boolean running;
	public World(String name, String[] b){
		this.name = name;
		running = true;
		biomes = new ArrayList<Biome>();
		for(String s : b){
			biomes.add(new Biome(s,
					biomeFiles[s.charAt(s.length()-1)%biomeFiles.length]));
		}
	}
	public Player getPlayer(String id){
		for(Player p : players)if(p.getID().equals(id))return p;
		return null;
	}
	public Player newPlayer(int i, int s, int v, String name, String id, String biomeName){
		Player p = new Player(i,s,v, name, id);
		players.add(p);
		for(Biome b : biomes)
			if(b.getName().equals(biomeName)){
				p.setBiome(b);
				return p;
			}
		Biome b = new Biome(biomeName, 
				biomeFiles[biomeName.charAt(biomeName.length()-1)%biomeFiles.length]);
		p.setBiome(b);
		biomes.add(b);
		return p;
	}
	@Override
	public void run() {
		while(running){
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update();
		}
	}
	public void update(){

		for(Biome b : biomes){
			b.update();
		}
	}
}
