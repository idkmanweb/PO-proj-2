/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Potek
 */
public class Guarana extends Plant {
    
    public Guarana(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Guarana";
        icon = new ImageIcon(getClass().getResource("resources/guarana.png"));
        init = 0;
        str = 0;
        x = X;
        y = Y;
        name = 'g';
        label = new JLabel();
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Guarana(x, y));
        world.getMap()[x][y] = 'g';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        attacker.addStr(3);
        world.getEntities().remove(this);
        gui.getLog().addFood(attacker, this);
        Collections.sort(world.getEntities());
        return 1;
    }
}
