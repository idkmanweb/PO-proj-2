/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package po.proj.pkg2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author Potek
 */
public class POProj2 {
    static int w = 20, h = 20;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        GUI gui = new GUI();
        SaveLoad saver = new SaveLoad();
        int start = 0;
        while(start == 0){
            start = gui.getStart();
        }
        World world = new World(gui.getW(), gui.getH(), gui);
        while(true){
            if(gui.getNEF() == 2){
                world.addNewEntity(gui.getN(), gui.getW(), gui.getH());
                gui.setNEF();
                gui.update(world);
            }
            if(gui.getFlag() == 1){
                saver.save(world);
                gui.setFlag();
            }
            if(gui.getFlag() == 2){
                saver.load(world);
                gui.update(world);
                gui.setFlag();
            }
            if(gui.getFlag() == 3){
                for(int i = 0; i < world.getEntities().size(); i++){
                    if(world.getEntities().get(i) instanceof Human){
                        world.getEntities().get(i).activatePower();
                        break;
                    }
                }
                gui.setFlag();
            }
            if(gui.getDir() != -1){
                world.doTurn(gui);
            }
        }
    }
}
