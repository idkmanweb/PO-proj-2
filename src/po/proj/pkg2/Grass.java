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
public class Grass extends Plant {

    public Grass(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Grass";
        icon = new ImageIcon(getClass().getResource("resources/grass.png"));
        init = 0;
        str = 0;
        x = X;
        y = Y;
        name = '/';
        label = new JLabel();
    }
    
    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Grass(x, y));
        world.getMap()[x][y] = '/';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
    }
    
}
