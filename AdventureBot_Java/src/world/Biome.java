package world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import world.beings.*;
import world.beings.special.Anubis;

public class Biome {
	static Pattern p = 
			Pattern.compile("(.+)\\t(\\d+)\\t(special)?(?:(\\d+) (\\d+) (\\d+) (?:\\[(\\d+),(\\d+),(\\d+),(\\d+)\\] \\[(.+),(.+),(.+),(.+)\\]))?");
	ArrayList<Being> currentEnemies;
	ArrayList<Player> players;
	/*static final String[] descriptors = 
		{
				"the blisteringly hot deserts of ",
				"the freezing cold tundra of ",
				"the humid jungle of ",
				"the watery marshes of ",
				"the corrupted village that used to be ",
				"the mountains of ",
				"the grassy plains of ",
				"the dark catacombs of "
		};
	static final String[][] enemies = {
			{"Anubis", "Sphinx", "crocodile", "snek", "scorpion", "swarm of beetles"},
			{"Santa Claus", "glacier", "polar bear", "swarm of rabid penguins", "snowball"},
			{"Tarzan", "living tree", "horde of chimps", "swarm of fire ants"},
			{"witch", "gelatinous cube", "swarm of mosquitoes", "toad"},
			{"corrupted hero", "corrupted soldier", "corrupted villager", "corrupted corn", "corrupted wheat"},
			{},
			{},
			{}
	};*/
	List<String> info;
	String name;
	public Biome(String s, File f){
		name = s;
		try {
			info = Files.readAllLines(f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentEnemies = new ArrayList<Being>();
		players = new ArrayList<Player>();
	}
	public void update(){
		if(currentEnemies.size()==0){
			currentEnemies.addAll(getEnemies());
		}
		for(Player p : players){
			p.update();
			if(!p.hasAttacked){
				p.attack(getRandomEnemy());
			}
		}
		for(Being e : currentEnemies){
			e.update();
			e.attack(players.get((int) (Math.random()*players.size())));
		}
	}
	public String getName() {
		return name;
	}
	public void addPlayer(Player p){
		players.add(p);
	}
	public void removePlayer(Player p){
		players.remove(p);
	}
	public void enemyKilled(Being b){
		currentEnemies.remove(b);
	}
	public ArrayList<Being> getEnemies(){
		ArrayList<Being> a = new ArrayList<Being>();
		int i = 0;
		for(Player p : players)i += p.getLevel();
		int j = 0;
		while(j < i+2){
			Matcher m = p.matcher(
					info.get((info.size()-1)*(int)(Math.random()*Math.random()+1)));
			if(Integer.parseInt(m.group(2))<i+2){
				if(m.group(3).equals("special")){
					a.add(getSpecial(m.group(1)));
				}else{
					a.add(new Being(
							Integer.parseInt(m.group(3)),
							Integer.parseInt(m.group(4)),
							Integer.parseInt(m.group(5)),
							new float[]{
									Float.parseFloat(m.group(6)),
									Float.parseFloat(m.group(7)),
									Float.parseFloat(m.group(8)),
									Float.parseFloat(m.group(9))
							},
							new float[]{
									Float.parseFloat(m.group(10)),
									Float.parseFloat(m.group(11)),
									Float.parseFloat(m.group(12)),
									Float.parseFloat(m.group(13))
							},
							"tom",
							m.group(1)
							));
				}
				j += Integer.parseInt(m.group(2));
			}
		}
		return a;
	}
	public Being getRandomEnemy(){
		return currentEnemies.get(
				(int) (Math.random()*currentEnemies.size())
			   );
	}
	public Being getSpecial(String s){
		switch(s){
		case "Anubis":return new Anubis();
		}
		return null;
	}
}
