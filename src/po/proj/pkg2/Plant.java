/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Potek
 */
public abstract class Plant extends Entity {
    
    @Override
    public void action(World world, int direction, int w, int h, GUI gui){
        int chance = (int) (Math.random()*100) % 40;
	if (chance == 1) {
		if (x < w-1 && world.getMap()[x+1][y] == ' ') {
                    addBirth(world, x+1, y, gui);
		}
		else if (x > 0 && world.getMap()[x-1][y] == ' ') {
			addBirth(world, x-1, y, gui);
		}
		else if (y > 0 && world.getMap()[x][y-1] == ' ') {
			addBirth(world, x, y-1, gui);
		}
		else if (y < h-1 && world.getMap()[x][y+1] == ' ') {
			addBirth(world, x, y+1, gui);
		}
	}
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        world.getEntities().remove(this);
        gui.getLog().addFood(attacker, this);
        world.sortEntities();
        return 1;
    }
}
