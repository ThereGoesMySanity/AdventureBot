package world.beings;

import java.util.ArrayList;
import java.util.HashMap;

import world.Biome;
import world.equipment.*;
public class Player extends Being{
	public enum Stats {INT, STR, VIT}
	public boolean levelUp;
	public boolean hasAttacked;
	Biome biome;
	String id;
	HashMap<Armor.ArmorType, Armor> armor;
	Weapon weapon;
	ArrayList<Spell> spells;
	ArrayList<Equipment> stored;


	public Biome getBiome() {
		return biome;
	}
	public void setBiome(Biome biome) {
		biome.addPlayer(this);
		this.biome = biome;
	}
	int level;
	int xp, xp_total;
	HashMap<Stats, Integer> stat;

	public Player(int i, int s, int v, String name, String id){
		super(v, 0, s, new float[]{1,0,0,0}, new float[]{1,1,1,1}, name, "human");
		stat = new HashMap<Stats, Integer>();
		stat.put(Stats.INT, i);
		stat.put(Stats.STR, s);
		stat.put(Stats.VIT, v);
		levelUp = false;
		this.id = id;
		armor = new HashMap<Armor.ArmorType, Armor>();
		stored = new ArrayList<Equipment>();
		hasAttacked = false;
	}
	@Override
	public void update(){
		hasAttacked = false;
		for(Armor a : armor.values()){
			a.onTick(this);
		}
	}
	@Override
	public void attack(Being other){
		hasAttacked = true;
		for(Armor a : armor.values()){
			a.onAttack(this, other);
		}
		super.attack(other);
	}
	@Override
	public void giveXP(int xp){
		this.xp += xp;
		xp_total += xp;
		if(this.xp >= 100*level){
			this.xp = 0;
			levelUp = true;
		}
	}
	@Override
	public void damage(int damage, float[] damageTypes, Being other){
		for(Armor a : armor.values()){
			a.onDefend(this, other);
		}
		super.damage(damage, damageTypes, other);
	}
	public void levelUp(Stats which){
		if(!levelUp)return;
		stat.put(which, stat.get(which)+1);
		level++;
		levelUp = false;
	}
	public void equip(Equipment e){
		if(e instanceof Armor){
			Armor a = (Armor)e;
			if(armor.get(a.type)!=null){
				Armor ar = armor.get(a.type);
				stored.add(ar);
				ar.onRemove(this);
			}
			armor.put(a.type, a);
			a.onEquip(this);
		}else if (e instanceof Weapon){
			
		}
	}
	public int getLevel() {
		return level;
	}
	public String toString(){
		return "a "+(stat.get(Stats.INT)/stat.get(Stats.STR)>0?"smart":"brave")+
				" level "+level+" adventurer named " + name;
	}
	@Override
	public boolean equals(Object p){
		if(p instanceof Player)
			return ((Player)p).id.equals(id);
		return false;
	}

	public String getID() {
		return id;
	}
	public Integer getStat(Stats s) {
		return stat.get(s);
	}
	public void setStat(Stats s, Integer i) {
		stat.put(s, i);
	}
}
