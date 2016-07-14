package main;

import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.*;
import net.dv8tion.jda.entities.*;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import world.World;
import world.beings.Player;

public class Main extends ListenerAdapter{
	HashMap<Guild, World> worlds;
	public static void main(String[] args){
		try {
			JDA jda = new JDABuilder().setBotToken("token").buildBlocking();
	        jda.addEventListener(new Main());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
    	Guild g = event.getGuild();
    	if(!worlds.containsKey(g)){
    		List<TextChannel> t = g.getTextChannels();
    		String[] s = new String[t.size()];
    		for(int i = 0; i < t.size(); i++){
    			s[i] = t.get(i).getName();
    		}
    		World w = new World(g.getName(), s);
    		new Thread(w, "Thread-"+g.getName()).start();
    		worlds.put(g, w);
    	}
    	Player p = worlds.get(g).getPlayer(event.getAuthor().getId());
    	if(p==null){
    		String n = event.getAuthorName();
    		p = worlds.get(g).newPlayer(
    				(n.charAt(0)%11)+8, 
    				(n.charAt(1)%11)+8, 
    				(n.charAt(2)%11)+8, 
    				n, event.getAuthor().getId(), 
    				event.getTextChannel().getName());
    	}
    	if(event.getMessage().getRawContent().charAt(0)%10>7){
    		p.attack(p.getBiome().getRandomEnemy());
    	}else{
    		p.castSpell(p.getBiome().getRandomEnemy(), p.getRandomSpell());
    	}
    }
}
