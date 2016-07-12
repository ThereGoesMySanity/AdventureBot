package world.equipment;

import world.beings.Player;

public abstract class Spell extends Equipment{
	int duration;
	public abstract void update();
	@Override
	public void onTick(Player p){
		duration -= 1;
		if(duration != 0){
			update();
		}
	}
}
