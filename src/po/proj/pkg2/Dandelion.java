/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Potek
 */
public class Dandelion extends Plant {
    
    public Dandelion(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Dandelion";
        icon = new ImageIcon(getClass().getResource("resources/dandelion.png"));
        init = 0;
        str = 0;
        x = X;
        y = Y;
        name = 'd';
        label = new JLabel();
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Dandelion(x, y));
        world.getMap()[x][y] = 'd';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
    }
    
    @Override
    public void action(World world, int direction, int w, int h, GUI gui){
        for(int i = 0; i < 3; i++){
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
    }
}
