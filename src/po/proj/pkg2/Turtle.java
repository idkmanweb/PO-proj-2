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
public class Turtle extends Animal {
    
    public Turtle(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Turtle";
        icon = new ImageIcon(getClass().getResource("resources/turtle.png"));
        init = 1;
        str = 2;
        x = X;
        y = Y;
        name = 't';
        label = new JLabel();
        gaveBirth = 0;
    }

    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Turtle(x, y));
        world.getMap()[x][y] = 't';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
        world.sortEntities();
    }
    
    @Override
    public void action(World world, int direction, int w, int h, GUI gui){
        int chance = (int) (Math.random()*100)%4;
        if(chance == 0){
        int dir = (int) (Math.random()*100)%4, nx = x, ny = y;
        if(dir == 0 && x < w-1){
            nx++;
        } else if (dir == 1 && x > 0){
            nx--;
        } else if (dir == 2 && y > 0){
            ny--;
        } else if (dir == 3 && y < h - 1){
            ny++;
        } else if (x < w-1){
            nx++;
        } else {
            nx--;
        }
        
        if(world.getMap()[nx][ny] == ' '){
            world.getMap()[nx][ny] = name;
            world.getMap()[x][y] = ' ';
            int ox = x, oy = y;
            x = nx;
            y = ny;
            gui.moveEntity(this, ox, oy);
        } else if (world.getMap()[nx][ny] == name) {
            int isOK = 1;
            for(int i = 0; i < world.getEntities().size(); i++){
                    if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                        isOK = world.getEntities().get(i).getBirth();
                    }
                }
            if(gaveBirth == 0 && isOK == 0){
                if(nx < w-1 && world.getMap()[nx+1][ny] == ' '){
                    this.addBirth(world, nx+1, ny, gui);
                    gaveBirth = 1;
                    for(int i = 0; i < world.getEntities().size(); i++){
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                        if(world.getEntities().get(i).getX() == nx+1 && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                    }
                } else if(nx > 0 && world.getMap()[nx-1][ny] == ' '){
                    this.addBirth(world, nx-1, ny, gui);
                    gaveBirth = 1;
                    for(int i = 0; i < world.getEntities().size(); i++){
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                        if(world.getEntities().get(i).getX() == nx-1 && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                    }
                } else if(ny < h-1 && world.getMap()[nx][ny+1] == ' '){
                    this.addBirth(world, nx, ny+1, gui);
                    gaveBirth = 1;
                    for(int i = 0; i < world.getEntities().size(); i++){
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny+1){
                            world.getEntities().get(i).setBirth(1);
                        }
                    }
                } else if(ny > 0 && world.getMap()[nx][ny-1] == ' '){
                    this.addBirth(world, nx, ny-1, gui);
                    gaveBirth = 1;
                    for(int i = 0; i < world.getEntities().size(); i++){
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                            world.getEntities().get(i).setBirth(1);
                        }
                        if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny-1){
                            world.getEntities().get(i).setBirth(1);
                        }
                    }
                }
            }
        } else {
            for(int i = 0; i < world.getEntities().size(); i++){
                if(world.getEntities().get(i).getX() == nx && world.getEntities().get(i).getY() == ny){
                    int collision = world.getEntities().get(i).collision(this, world, gui);
                        if(collision == 1) {
                             world.getMap()[nx][ny] = name;
                             world.getMap()[x][y] = ' ';
                             int ox = x, oy = y;
                             x = nx;
                             y = ny;
                             gui.moveEntity(this, ox, oy);
                        } else if (collision == 0){
                             world.getEntities().remove(this);
                             world.getMap()[x][y] = ' ';
                             gui.killEntity(this);
                        }
                    
                }
            }
        }
        }
        
        
            Collections.sort(world.getEntities());
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        if(attacker.getStr() >= str){
            if(attacker.getStr() < 5){
                return 2;
            }
            world.getEntities().remove(this);
            gui.getLog().addKill(attacker, this);
            return 1;
        }
        gui.getLog().addKill(this, attacker);
        return 0;
    }
}


