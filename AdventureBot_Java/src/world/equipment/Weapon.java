package world.equipment;

import world.beings.Being;
import world.beings.Player;

public class Weapon extends Equipment{
	String name;
	int dmgMult, dmgPlus;
	public Weapon(int damageMult, int damagePlus, String name){
		dmgMult = damageMult;
		dmgPlus = damagePlus;
		this.name = name;
	}
	@Override
	public void onEquip(Player self) {
	}

	@Override
	public void onRemove(Player self) {
	}

	@Override
	public void onAttack(Player self, Being other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDefend(Player self, Being other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(Player self) {
		// TODO Auto-generated method stub
		
	}

}
