/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import po.proj.pkg2.Animal;

/**
 *
 * @author Potek
 */
public class Borscht extends Plant {

    public Borscht(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Borscht";
        icon = new ImageIcon(getClass().getResource("resources/borscht.png"));
        init = 0;
        str = 10;
        x = X;
        y = Y;
        name = 'b';
        label = new JLabel();
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Borscht(x, y));
        world.getMap()[x][y] = 'b';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        gui.getLog().addKill(this, attacker);
        return 0;
    }
    
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
        if (x < w-1 && world.getMap()[x+1][y] != 'b' && world.getMap()[x+1][y] != ' ' ) {
            for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == x+1 && world.getEntities().get(i).getY() == y && Animal.class.isAssignableFrom(world.getEntities().get(i).getClass())){
                    gui.killEntity(world.getEntities().get(i));
                    world.getEntities().remove(world.getEntities().get(i));
                    world.getMap()[x+1][y] = ' ';
                    break;
                }
            }
        }
	else if (x > 0 && world.getMap()[x-1][y] != 'b' && world.getMap()[x-1][y] != ' ') {
            for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == x-1 && world.getEntities().get(i).getY() == y && Animal.class.isAssignableFrom(world.getEntities().get(i).getClass())){
                    gui.killEntity(world.getEntities().get(i));
                    world.getEntities().remove(world.getEntities().get(i));
                    world.getMap()[x-1][y] = ' ';
                    break;
                }
            }
        }
        else if (y < h-1 && world.getMap()[x][y+1] != 'b' && world.getMap()[x][y+1] != ' ') {
            for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == x && world.getEntities().get(i).getY() == y+1 && Animal.class.isAssignableFrom(world.getEntities().get(i).getClass())){
                    gui.killEntity(world.getEntities().get(i));
                    world.getEntities().remove(world.getEntities().get(i));
                    world.getMap()[x][y+1] = ' ';
                    break;
                }
            }
        }
        else if (y > 0 && world.getMap()[x][y-1] != 'b' && world.getMap()[x][y-1] != ' ') {
           for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == x && world.getEntities().get(i).getY() == y-1 && Animal.class.isAssignableFrom(world.getEntities().get(i).getClass())){
                    gui.killEntity(world.getEntities().get(i));
                    world.getEntities().remove(world.getEntities().get(i));
                    world.getMap()[x][y-1] = ' ';
                    break;
                }
            }
        }
    }
}
