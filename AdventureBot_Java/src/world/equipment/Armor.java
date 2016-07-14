package world.equipment;

import world.beings.ArmorBuff;
import world.beings.Being;

public class Armor extends Equipment{
	public enum ArmorType {HELMET, GLOVES, CHEST, LEGGINGS, BOOTS}
	public ArmorType type;
	ArmorBuff a;
	String name;
	public Armor(ArmorType t, float[] resist, String name){
		type = t;
		this.name = name;
		a = new ArmorBuff(resist);
	}
	@Override
	public void onEquip() {
		player.addBuff(a);
	}
	@Override
	public void onRemove() {
		player.removeBuff(a);
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
