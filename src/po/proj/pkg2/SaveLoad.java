/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Potek
 */
public class SaveLoad{
    public SaveLoad() throws FileNotFoundException, IOException{}
    
    public void save(World world) throws IOException{
        FileWriter save = new FileWriter("save.txt");
        save.write(world.getWidth()+" "+world.getHeight()+" "+world.getTurn()+" ");
        save.write(world.getEntities().size()+" ");
        for(int i = 0; i < world.getEntities().size(); i++){
            save.write(world.getEntities().get(i).getX()+" "+world.getEntities().get(i).getY()+" " +world.getEntities().get(i).getName()+" ");
        }
        for(int i = 0; i < world.getEntities().size(); i++){
            if(world.getEntities().get(i) instanceof Human){
                save.write(world.getEntities().get(i).getPowerReady() + " " + world.getEntities().get(i).getCooldown() + " " +world.getEntities().get(i).getMovement());
                break;
            }
        }
        save.close();
    }
    
    public void load(World world) throws IOException, ClassNotFoundException{
       world.clear();
            Scanner scanner = new Scanner(new File("save.txt"));
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int turn = scanner.nextInt();
            int size = scanner.nextInt();
            int x, y;
            char name;
            for (int i = 0; i < size; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                name = scanner.next().charAt(0);
                Entity newOrg = null;
                switch (name)
                {
                    case 'w':
                        newOrg = new Wolf(x, y);
                        break;
                    case 's':
                        newOrg = new Sheep(x, y);
                        break;
                    case 'f':
                        newOrg = new Fox(x, y);
                        break;
                    case 't':
                        newOrg = new Turtle(x, y);
                        break;
                    case 'a':
                        newOrg = new Antelope(x, y);
                        break;
                    case '/':
                        newOrg = new Grass(x, y);
                        break;
                    case 'd':
                        newOrg = new Dandelion(x, y);
                        break;
                    case 'g':
                        newOrg = new Guarana(x, y);
                        break;
                    case 'n':
                        newOrg = new Nightshade(x, y);
                        break;
                    case 'b':
                        newOrg = new Borscht(x, y);
                        break;
                    case 'h':
                        newOrg = new Human(x, y);
                        break;
                }
                world.addLoadedEntity(newOrg);
            }
            int ready = scanner.nextInt();
            int cooldown = scanner.nextInt();
            int movement = scanner.nextInt();
            for(int i = 0; i < size; i++){
                if(world.getEntities().get(i) instanceof Human){
                    world.getEntities().get(i).setPowerReady(ready);
                    world.getEntities().get(i).setCooldown(cooldown);
                    world.getEntities().get(i).setMovement(movement);
                }
            }
            world.generateMap();
            scanner.close();
    }
}
