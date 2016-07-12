package world.beings.special;

import world.beings.*;

public class Anubis extends Being{

	public Anubis() {
		super(2000, 4000, 20, new float[]{0,0,0,1}, 
				new float[]{1,1,1,0.8f}, "Anubis", "");
	}
	public void attack(Being other){
		if(other instanceof Player){
			Player p = ((Player)other);
			other.damage(
					attack*
					p.getStat(Player.Stats.STR)/
					p.getStat(Player.Stats.INT),
					attackTypes, this
			);
		}
	}
	
	@Override
	public String getAttackString(Being other){
		if(!(other instanceof Player))return super.getAttackString(other);
		Player p = (Player)other;
		return "Anubis weighs "+p.getName()+"'s soul and declares them "+
		(int)(p.getStat(Player.Stats.STR)*100f/
					p.getStat(Player.Stats.INT))+"% guilty";
	}
}
