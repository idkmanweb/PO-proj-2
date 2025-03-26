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
public class Wolf extends Animal {
    public Wolf(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Wolf";
        icon = new ImageIcon(getClass().getResource("resources/wolf.png"));
        init = 5;
        str = 9;
        x = X;
        y = Y;
        name = 'w';
        label = new JLabel();
        gaveBirth = 0;
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Wolf(x, y));
        world.getMap()[x][y] = 'w';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
        world.sortEntities();
    }
}
