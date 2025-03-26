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
public class Nightshade extends Plant {
    
     public Nightshade(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Nightshade";
        icon = new ImageIcon(getClass().getResource("resources/nightshade.png"));
        init = 0;
        str = 99;
        x = X;
        y = Y;
        name = 'n';
        label = new JLabel();
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Nightshade(x, y));
        world.getMap()[x][y] = 'n';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        gui.getLog().addKill(this, attacker);
        return 0;
    }
}
