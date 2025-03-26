/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.io.Serializable;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Potek
 */

public abstract class Entity implements Comparable<Entity>, Serializable {
    protected String $type;
    protected JLabel label;
    protected int x, y, init, str;
    protected char name;
    protected ImageIcon icon;
    
    public abstract void action(World world, int direction, int w, int h, GUI gui);
    
    public abstract int collision(Entity attacker, World world, GUI gui);
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getInit(){
        return init;
    }
    
    public int getStr(){
        return str;
    }
    
    public char getName(){
        return name;
    }
    
    public ImageIcon getIcon(){
        return icon;
    }
    
    @Override
    public int compareTo(Entity entity)
    {
        return entity.getInit() - this.getInit();
    }
    
    public abstract void addBirth(World world, int x, int y, GUI gui);
    
    public int getBirth(){
        return 0;
    }
    
    public void setBirth(int yes){
    }
    
    public void addStr(int nstr){
        str += nstr;
    }
    
    public int getPowerReady(){
        return 0;
    }
    
    public int getCooldown(){
        return 0;
    }
    
    public int getMovement(){
        return 0;
    }
    
    public void setPowerReady(int ready){
    }
    
    public void setCooldown(int cd){
    }
    
    public void setMovement(int move){
    }
    
    public void activatePower(){}
}
