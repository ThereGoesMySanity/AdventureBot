package world.beings;

import java.util.HashMap;


public class Being {
	
	String name, type;

	protected int hp, hp_max, xp_given;

	protected int attack;
	protected float[] attackTypes;

	protected float[] resist;
	HashMap<Being, Integer> damageDone;
	
	public Being(int hp, int xp, int attack, float[] attackTypes, float[] resist, 
			String name, String type){
		
		this.hp = hp_max = hp;
		this.xp_given = xp;
		this.attack = attack;
		this.attackTypes = attackTypes;
		this.resist = resist;
		this.name = name;
		this.type = type;
		damageDone = new HashMap<Being, Integer>();
	}
	
	public void damage(int damage, float[] damageTypes, Being opponent){
		float totalDamage = 0;
		for(int x = 0; x < damageTypes.length; x++){
			totalDamage += damage*damageTypes[x]*resist[x];
		}
		damageDone.put(opponent, (int)totalDamage);
		hp -= (int)totalDamage;
		if(hp <= 0)die();
	}
	
	public void attack(Being opponent){
		opponent.damage(attack, attackTypes, this);
	}
	
	public void heal(int amount){
		hp += amount;
	}
	
	public void giveXP(int xp){}
	
	public void update(){}
	
	public void die(){
		int i = 0;
		for(int j : damageDone.values())
			i+=j;
		for(Being x : damageDone.keySet())
			x.giveXP(damageDone.get(x)/i);
	}
	
	public void addResist(float[] rs){
		for(int i = 0; i < rs.length; i++){
			resist[i] *= rs[i];
		}
	}
	
	public void setResist(int i, float r){
		resist[i] = r;
	}
	public String getAttackString(Being other){
		return "A " + type + " atttacks "+other.name;
	}
	
	public String getName() {
		return name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String toString(){
		String n = "";
		switch(type.charAt(0)){
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			n = "n";
			break;
		}
		return "a"+n+" " + type + " named "+name;
	}
}
