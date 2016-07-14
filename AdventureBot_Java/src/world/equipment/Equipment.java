package world.equipment;

import world.beings.*;

public abstract class Equipment {
	Player player;
	public abstract void onEquip();
	public abstract void onRemove();
	public abstract void onAttack(Being other);
	public abstract void onDefend(Being other);
	public abstract void onTick();
	public void equip(Player p){
		player = p;
		onEquip();
	}
	public void unequip(){
		onRemove();
		player = null;
	}
}
