package world.equipment;

import java.util.HashMap;

import world.beings.Being;

public abstract class Spell extends Equipment{
	HashMap<Being, Integer> casts;
	public Spell(){
		casts = new HashMap<Being, Integer>();
	}
	public void cast(Being b){
		casts.put(b, getDuration());
		onCast(b);
	}
	@Override
	public void onTick(){
		for(Being b : casts.keySet()){
			if(casts.get(b)==0){
				onExpire(b);
				casts.remove(b);
			}else{
				update(b);
				casts.put(b, casts.get(b)-1);
			}
		}
	}
	public abstract int getDuration();
	public abstract void onCast(Being b);
	public abstract void onExpire(Being b);
	public abstract void update(Being b);
}