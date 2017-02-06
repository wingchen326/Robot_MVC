package PaSkCode;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GWView extends PSysView {

	GWView(){
		
	}
	
	void draw(GWModel gw, Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Sprite player = gw.player;
		g2d.drawImage(player.image, player.x-player.radius, player.y-player.radius,
					  player.radius*2, player.radius*2, null);
		
		for(int i = 0; i < gw.B_List.size(); i++){
			Sprite bot = gw.B_List.get(i);
			g2d.drawImage(bot.image, bot.x-bot.radius, bot.y-bot.radius, bot.radius*2, bot.radius*2, null);
		}
		
		for(int j = 0; j < gw.Projectile_List.size(); j++){
			Sprite projectile  = gw.Projectile_List.get(j);
			g2d.drawImage(projectile.image, projectile.x-projectile.radius, 
						  projectile.y-projectile.radius, projectile.radius*2, projectile.radius*2, null);
		}
			
	}
	
	
}
