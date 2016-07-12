package world.equipment;

import world.beings.*;

public abstract class Equipment {
	public abstract void onEquip(Player self);
	public abstract void onRemove(Player self);
	public abstract void onAttack(Player self, Being other);
	public abstract void onDefend(Player self, Being other);
	public abstract void onTick(Player self);
}
