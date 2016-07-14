package world.beings;

import java.util.Arrays;
import java.util.List;

public class Buff {
	float dmgMult;
	int dmgPlus;
	float[] dist;
	public Buff(float dmgMult, int dmgPlus, float[] dist){
		this.dmgMult=dmgMult;
		this.dmgPlus=dmgPlus;
		this.dist = dist;
	}
	public static void applyBuffs(List<Buff> b, Player p){
		float dmgMult = 1;
		int dmgPlus = 0;
		float sum = 1;
		float[] dist = {1,0,0,0};
		for(Buff buff : b){
			dmgMult*=buff.dmgMult;
			dmgPlus+=buff.dmgPlus;
			for(int i = 0; i < 4; i++){
				dist[i]+=buff.dist[i];
				sum += buff.dist[i];
			}
		}
		for(int i = 0; i < 4; i++){
			dist[i]/=sum;
		}
		p.setAttack((int) (p.getStat(Player.Stats.STR)*dmgMult+dmgPlus));
		p.attackTypes = dist;
	}
	public boolean equals(Object o){
		if(o instanceof Buff){
			Buff b = (Buff)o;
			return dmgMult==b.dmgMult&&dmgPlus==b.dmgPlus&&Arrays.equals(dist, b.dist);
		}
		return false;
	}
}
