/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.io.Serializable;

/**
 *
 * @author Potek
 */
public class Logger implements Serializable {
    private String log;
    
    public Logger() {
	log = "";
    }

    public void addKill(Entity attacker, Entity prey) {
            log += "\n";
            log += attacker.getName();
            log += " zabil ";
            log += prey.getName();
            log += " w (";
            log += Integer.toString(attacker.getX());
            log += ",";
            log += Integer.toString(attacker.getY());
            log += ")";
    }

    public void addFood(Entity animal, Entity food) {
            log += "\n";
            log += animal.getName();
            log += " zjadl ";
            log += food.getName();
            log += " w (";
            log += Integer.toString(animal.getX());
            log += ",";
            log += Integer.toString(animal.getY());
            log += ")";
    }

    public void addBirth(Entity animal) {
            log += "\n";
            log += animal.getName();
            log += " urodzil sie w (";
            log += Integer.toString(animal.getX());
            log += ",";
            log += Integer.toString(animal.getY());
            log += ")";
    }

    public void addPlant(Entity plant) {
            log += "\n";
            log += plant.getName();
            log += " rozpylil sie w (";
            log += Integer.toString(plant.getX());
            log += ",";
            log += Integer.toString(plant.getY());
            log += ")";
    }
    
    public void addTurn(int turn) {
            log += "\n -- Tura ";
            log += turn;
            log += " --";
    }

    public String write() {
            return log;
    }

    public void clear() {
            log = "";
    }
}
