package PaSkCode;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class GWModel extends PSysModel{
    
    Image  projectile_img, grave_img;
    int vx, vy;                         // using velocity to define projectile direction. 
   
    Sprite player;
    ArrayList<Sprite> B_List; 			// ArrayList to store Bots.
    ArrayList<Sprite> Projectile_List;  // ArrayList to store Projectile.
    
// Create three kinds of sprites: player, Bot and Projectile.
GWModel(){
     player = new Sprite();
     B_List = new ArrayList<Sprite>();
     Projectile_List = new ArrayList<Sprite>();
   
}
    
void addBot(int rad, int nx, int ny, int vx, int vy, Image img){
        B_List.add(new Sprite(rad, nx, ny, vx, vy, img));
}
    
void addPlayer(int rad, int nx, int ny, int vx, int vy, Image img){
        player = new Sprite(rad, nx, ny, vx, vy, img);
}
    
void addGraveStone (Image img){
       grave_img = img;
}
    
void addProjectileImg (Image img){
       projectile_img = img;
}
    
void addProjectile (Sprite p1){
        if(p1.velX == 0)
            vx = 0;
        else if (p1.velX > 0)
            vx = 1; 
        else 
            vx = -1;
        
        if(p1.velY == 0)
            vy= 0;
        else if (p1.velY > 0)
            vy = 1;
        else 
            vy = -1;
        
        //initial projectiles from player or bot.
        Projectile_List.add(new Sprite(10, p1.x + vx * (p1.radius + 10),
                                       p1.y + vy * (p1.radius + 10),
                                       p1.velX *2, p1.velY *2, projectile_img));
        }


// check if sprite alive or die.
boolean spriteAlive (Sprite s1){
	if(s1.image != grave_img)
		return true;
	else 
		return false;
	
}
    
// Orient bots always move toward player.
void orientBots(){
      for(int i = 0; i < B_List.size(); i++){
         // check if Bot is alive or die.
        if( spriteAlive(B_List.get(i)) ){   	
            if(B_List.get(i).x > player.x && B_List.get(i).y > player.y ){
                B_List.get(i).velX = -1;
                B_List.get(i).velY = -1;
            }
            if(B_List.get(i).x < player.x && B_List.get(i).y < player.y ){

                B_List.get(i).velX = 1;
                B_List.get(i).velY = 1;
            }
            if(B_List.get(i).x > player.x && B_List.get(i).y < player.y ){
                
                B_List.get(i).velX = -1;
                B_List.get(i).velY = 1;
            }
            if(B_List.get(i).x < player.x && B_List.get(i).y > player.y ){

                B_List.get(i).velX = 1;
                B_List.get(i).velY = -1;
            }
            
            // call method to fire projectile from each Bot in arrayList .
            addProjectile(B_List.get(i));
            
            }

        }
}
    
void keyPressed (KeyEvent e) {
         // check if player is alive or not.
		if(spriteAlive(player)){
			// conditions check player doesn't fire projectiles with stationary position.
             if(e.getKeyCode() == KeyEvent.VK_SPACE && (player.velX != 0 || player.velY != 0)){
                 addProjectile(player);
                }
             if (e.getKeyCode() == KeyEvent.VK_UP) {
                 player.velY = -5;
             }
             if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                 player.velY = +5;
             }
             if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                 player.velX = -5;
             }
             if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                 player.velX = +5;
             }
         }
}
    
void keyReleased (KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.velY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.velY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.velX = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.velX = 0;
        }
        
}
    
//Check for sprites collision.
void checkCollide(){
	
        // Bot and player collision
        for(int i = 0; i < B_List.size(); i++){
            if( isOverlap(player,B_List.get(i)) ){
                player.velY = 0;
                player.velX = 0;
                B_List.get(i).velX = 0;
                B_List.get(i).velY = 0;
                player.image = grave_img;
                B_List.get(i).image = grave_img;
            }
        }
        
      // Bot and Projectile collision
        for(int i = 0; i < B_List.size(); i++){
            for(int j = 0; j < Projectile_List.size();j++){
                if(isOverlap(B_List.get(i), Projectile_List.get(j))){
                    B_List.get(i).velX = 0;
                    B_List.get(i).velY = 0;
                    B_List.get(i).image = grave_img;
                    Projectile_List.remove(j);
                }
            }
        }

        // Projectile and player collision
        for(int i = 0; i < Projectile_List.size(); i++){
            if(isOverlap(player,Projectile_List.get(i))){
                player.velY = 0;
                player.velX = 0;
                player.image = grave_img;
                Projectile_List.remove(i);
            }
        }
    
        // bot and bot collision
        for(int i = 0; i < B_List.size(); i++){
            for(int j = i+1; j < B_List.size(); j++){
                if(isOverlap(B_List.get(i), B_List.get(j))){
                    B_List.get(j).velX = 0;
                    B_List.get(j).velY = 0;
                    B_List.get(i).velX = 0;
                    B_List.get(i).velY = 0;
                    B_List.get(j).image = grave_img;
                    B_List.get(i).image = grave_img;
                }
            }
        }
    
         //Projectile and Projectile collision
        for(int i = 0; i < Projectile_List.size(); i++){
            for(int j = i+1; j < Projectile_List.size(); j++){
             if(isOverlap(Projectile_List.get(i), Projectile_List.get(j))){          
                 Projectile_List.get(i).velX = 0;
                 Projectile_List.get(i).velY = 0;
                 Projectile_List.get(j).velX = 0;
                 Projectile_List.get(j).velY = 0;
                 Projectile_List.remove(j);
                 Projectile_List.remove(i);
             }
            }
        }
    
}
        
 //Check for sprites overlap.
boolean isOverlap(Particle p1, Particle p2){
        int diffX = Math.abs(p1.x - p2.x);
        int diffY = Math.abs(p1.y - p2.y);
        if (diffX < p1.radius + p2.radius && diffY < p1.radius + p2.radius)
            return true;
        else
            return false;
}
    

//update all sprites in frame.
void update(int bw, int bh){
	
	 	player.update(bw,bh);
        for(int i = 0; i < B_List.size(); i++){
            B_List.get(i).update(bw, bh);
        }
        for(int j = 0; j < Projectile_List.size(); j++){
            Projectile_List.get(j).update(bw, bh);
        }    
    }
   
}
