package world.equipment;

import world.beings.Being;
import world.beings.Buff;

public class Weapon extends Equipment{
	String name;
	Buff buff;
	public Weapon(float damageMult, int damagePlus, float[] dist, String name){
		buff = new Buff(damageMult, damagePlus, new float[]{1,0,0,0});
		this.name = name;
	}
	public Weapon(float damageMult, int damagePlus, String name){
		this(damageMult, damagePlus, new float[]{1,0,0,0}, name);
	}
	@Override
	public void onEquip() {
		player.addBuff(buff);
	}

	@Override
	public void onRemove() {
		player.removeBuff(buff);
	}

	@Override
	public void onAttack(Being other) {
		
	}

	@Override
	public void onDefend(Being other) {
		
	}

	@Override
	public void onTick() {
		
	}

}
