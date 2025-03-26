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
public class Sheep extends Animal {
    public Sheep(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Sheep";
        icon = new ImageIcon(getClass().getResource("resources/sheep.png"));
        init = 4;
        str = 4;
        x = X;
        y = Y;
        name = 's';
        label = new JLabel();
        gaveBirth = 0;
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Sheep(x, y));
        world.getMap()[x][y] = 's';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
        world.sortEntities();
    }
}
