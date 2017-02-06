package PaSkCode;

import java.awt.Graphics2D;
import java.awt.Image;

public class Sprite extends Particle {
	
	 Image image; // image to display for sprite

	    //Constructor
	    Sprite() {
	    }
	    	  
	    // Sprite(int r, int nx, int ny, int vx, int vy, Image img) {
	    // instantiate new sprite
	    // r: radius
	    // nx, ny: position (x, y)
	    // vx, vy: velocity (x, y)
	    // img: image to display
	    Sprite(int r, int nx, int ny, int vx, int vy, Image img) {
	    	super(r,nx,ny,vx,vy);
	    	image = img;
	    }
	    
	    void draw(Graphics2D g) {
		g.drawImage(image, x-radius, y-radius, radius*2, radius*2, null);
	    }   

	}

