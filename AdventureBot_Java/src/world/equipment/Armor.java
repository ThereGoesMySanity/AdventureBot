package world.equipment;

public abstract class Armor extends Equipment{
	public enum ArmorType {HELMET, GLOVES, CHEST, LEGGINGS, BOOTS}
	public ArmorType type;
	public Armor(ArmorType t){
		type = t;
	}
}
