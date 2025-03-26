/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Potek
 */
public class Human extends Animal {
    private int ready, cooldown, movement;
    
    public Human(){
        icon = new ImageIcon(getClass().getResource("resources/human.png"));
        x = 1;
        y = 1;
        name = 'h';
        ready = 1;
        cooldown = 0;
        movement = 1;
        label = new JLabel();
        gaveBirth = 0;
    }
    
    public Human(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Human";
        icon = new ImageIcon(getClass().getResource("resources/human.png"));
        init = 4;
        str = 5;
        x = X;
        y = Y;
        name = 'h';
        ready = 1;
        cooldown = 0;
        label = new JLabel();
        gaveBirth = 0;
        movement = 1;
    }
    
    @Override
    public void action(World world, int direction, int w, int h, GUI gui){
        int nx = x, ny = y;
        if(direction == 0 && x > movement - 1){
            nx -= movement;
        } else if(direction == 1 && x < w-movement){
            nx += movement;
        } else if(direction == 2 && y > movement-1){
            ny -= movement;
        } else if(direction == 3 && y < h-movement){
            ny += movement;
        }
        
        if(cooldown > 7){
            cooldown--;
            movement = 2;
        } else if (cooldown < 8 && cooldown > 5){
            int chance = (int) (Math.random()*100)%2;
            if(chance == 0){
                movement = 2;
            } else {
                movement = 1;
            }
            cooldown--;
        } else if (cooldown > 0){
            cooldown--;
            movement = 1;
        } else if(cooldown == 0){
            ready = 1;
        }
        
        if(world.getMap()[nx][ny] == ' '){
            int ox = x, oy = y;
            world.getMap()[nx][ny] = name;
            world.getMap()[x][y] = ' ';
            x = nx;
            y = ny;
            gui.moveEntity(this, ox, oy);
        } else {
            for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny && world.getEntities().get(i).getName() != name ){
                   int collision = world.getEntities().get(i).collision(this, world, gui);
                    if(collision == 1) {
                        world.getMap()[nx][ny] = name;
                        world.getMap()[x][y] = ' ';
                        gui.moveEntity(this, x, y);
                        x = nx;
                        y = ny;
                   } else if (collision == 0){
                        world.getEntities().remove(this);
                        Collections.sort(world.getEntities());
                        world.getMap()[x][y] = ' ';
                   }
                }
            }
        }
    }

    @Override
    public void addBirth(World world, int x, int y, GUI gui) {}
    
    @Override
    public int getPowerReady(){
        return ready;
    }
    
    @Override
    public void activatePower(){
        if(ready == 1){
            ready = 0;
            cooldown = 9;
            movement = 2;
        }
    }
    
    @Override
     public int getCooldown(){
        return cooldown;
    }
    
    @Override
    public int getMovement(){
        return movement;
    }
    
    @Override
    public void setPowerReady(int ready){
        this.ready = ready;
    }
    
    @Override
    public void setCooldown(int cd){
        cooldown = cd;
    }
    
    @Override
    public void setMovement(int move){
        movement = move;
    }
}
