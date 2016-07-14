package world.beings;

import java.util.List;

public class ArmorBuff {
	float[] dist;
	public ArmorBuff(float[] dist){
		this.dist = dist;
	}
	public static void applyBuffs(List<ArmorBuff> b, Player p){
		float[] distr = {1,1,1,1};
		for(ArmorBuff a : b){
			for(int i = 0; i < 4; i++){
				distr[i]*=a.dist[i];
			}
		}
		p.resist=distr;
	}
}
