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
public class Antelope extends Animal {
    public Antelope(@JsonProperty("x") int X, @JsonProperty("y") int Y){
        $type = "po.proj.pkg2.Antelope";
        icon = new ImageIcon(getClass().getResource("resources/antelope.png"));
        init = 4;
        str = 4;
        x = X;
        y = Y;
        name = 'a';
        label = new JLabel();
        gaveBirth = 0;
    }

    @Override
    public void addBirth(World world, int x, int y, GUI gui) {
        world.getEntities().add(new Antelope(x, y));
        world.getMap()[x][y] = 'a';
        gui.addEntity(this);
        gui.getLog().addBirth(this);
        world.sortEntities();
    }
    
    @Override
    public void action(World world, int direction, int w, int h, GUI gui){
        int dir = (int) (Math.random()*100)%4, nx = x, ny = y;
        if(dir == 0 && x < w-2){
            nx+=2;
        } else if (dir == 1 && x > 1){
            nx-=2;
        } else if (dir == 2 && y > 1){
            ny-=2;
        } else if (dir == 3 && y < h - 2){
            ny+=2;
        } else if (x < w-2){
            nx+=2;
        } else {
            nx-=2;
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
        
       
            Collections.sort(world.getEntities());
    }
    
    @Override
    public int collision(Entity attacker, World world, GUI gui){
        int chance = (int) (Math.random()*100)%2;
        if(attacker.getStr() >= str){
            if(chance == 0){
                if(x < world.getWidth()-1 && world.getMap()[x+1][y] == ' '){
                    world.getMap()[x+1][y] = 'a';
                    world.getMap()[x][y] = ' ';
                    x++;
                    return 2;
                } else if(x > 0 && world.getMap()[x-1][y] == ' '){
                    world.getMap()[x-1][y] = 'a';
                    world.getMap()[x][y] = ' ';
                    x--;
                    return 2;
                } else if(y < world.getHeight()-1 && world.getMap()[x][y+1] == ' '){
                    world.getMap()[x][y+1] = 'a';
                    world.getMap()[x][y] = ' ';
                    y++;
                    return 2;
                } else if(y > 0 && world.getMap()[x][y-1] == ' '){
                    world.getMap()[x][y-1] = 'a';
                    world.getMap()[x][y] = ' ';
                    y--;
                    return 2;
                }
            }
            world.getEntities().remove(this);
            gui.getLog().addKill(attacker, this);
            return 1;
        }
        gui.getLog().addKill(this, attacker);
        return 0;
    }
}
