/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Potek
 */
public class World implements Serializable {
    private int height, width, numberOfEntities, turn;
    private char map[][];
    private Vector <Entity> entities;
    
    
    
    public World( int w, int h, GUI gui){
        turn = 0;
        numberOfEntities = 0;
        height = h;
        width = w;
        entities = new Vector<>();
        //get w and h
        gui.start(w, h);
        map = new char[width][height];
        for(int i = 0; i < width;  i++){
            for(int j = 0; j < height; j++){
                map[i][j] = ' ';
            }
        }
        int tempx, tempy;
        tempx = (int) (Math.random()*100)%width;
        tempy = (int) (Math.random()*100)%height;
        entities.add(new Human(width/2, height/2));
        map[width/2][height/2] = entities.get(numberOfEntities).getName();
        gui.addEntity(entities.get(numberOfEntities));
        numberOfEntities++;
        for (int i = 0; i < (width * height) / 100 - 1; i++){
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Fox(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;

            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Sheep(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Wolf(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Turtle(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Antelope(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Borscht(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Dandelion(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Grass(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Guarana(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
            while(map[tempx][tempy] != ' '){
                tempx = (int) (Math.random()*100)%width;
                tempy = (int) (Math.random()*100)%height;
            }
            entities.add(new Nightshade(tempx, tempy));
            map[tempx][tempy] = entities.get(numberOfEntities).getName();
            gui.addEntity(entities.get(numberOfEntities));
            numberOfEntities++;
        }
        Collections.sort(entities);
    }
    
    public void doTurn(GUI gui){
        
        if(gui.getDir() != -1){
            turn++;
            gui.getLog().addTurn(turn);
            for(int i = 0; i < entities.size(); i++){
                entities.get(i).action(this, gui.getDir(), width, height, gui);
            }
            for(int i = 0; i < entities.size(); i++){
                entities.get(i).setBirth(0);
            }
            Collections.sort(entities);
            gui.setDir(-1);
            gui.getLog().write();
            gui.update(this);
        }
    }

    public char[][] getMap(){
        return map;
    }
    
    public Vector<Entity> getEntities(){
        return entities;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public void setEntities(Vector<Entity> vector){
        entities = vector;
    }
    
    public void addNewEntity(char name, int x, int y){
        if(map[x][y] == ' '){
            Entity newOrg = null;
                map[x][y] = name;
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
                }
                entities.add(newOrg);
        }
    }
    
    public void clear(){
        map = null;
        entities = null;
        entities = new Vector<Entity>();
    }
    
    public void loadMap(int w, int h, int t, int size){
        width = w;
        height = h;
        turn = t;
        numberOfEntities = size;
    }
    
    public void addLoadedEntity(Entity org){
        entities.add(org);
    }
    
    public void sortEntities(){
        Collections.sort(entities);
    }
    
    public void generateMap(){
        map = new char[width][height];
        for(int i = 0; i < width;  i++){
            for(int j = 0; j < height; j++){
                map[i][j] = ' ';
            }
        }
        for(int i = 0; i < entities.size(); i++){
            map[entities.get(i).getX()][entities.get(i).getY()] = entities.get(i).getName();
        }
    }

}
