/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po.proj.pkg2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Potek
 */
public class GUI extends JFrame implements KeyListener, ActionListener {
    private JFrame popup;
    private JPanel boardPanel, menu, startup, newEntityList;
    private JButton[][] buttons;
    private JButton[] species;
    private int direction, flag, w, h, start, newEntityFlag;
    private Logger log;
    private JTextArea logs;
    private JScrollPane scroll;
    private JSpinner spinnerw, spinnerh;
    private char name;
    
    public GUI(){
        newEntityFlag = 0;
        start = 0;
        direction = -1;
        this.setTitle("AAAAAAAAAA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLayout(null);
        this.setVisible(true);
        startup = new JPanel();
        startup.setBounds(0, 0, 400, 200);
        startup.setLayout(null);
        SpinnerModel model1 = new SpinnerNumberModel(20, 10, 100, 1);  
        SpinnerModel model2 = new SpinnerNumberModel(20, 10, 100, 1);    
        spinnerw = new JSpinner(model1);
        spinnerh = new JSpinner(model2);
        spinnerw.setSize(50,20);
        spinnerw.setLocation(50, 90);
        spinnerh.setSize(50,20);
        spinnerh.setLocation(150, 90);
        JButton submit = new JButton("Start");
        submit.setSize(90,20);
        submit.setLocation(250, 90);
        submit.addActionListener(this);
        startup.setPreferredSize(new Dimension(400, 200));
        startup.add(spinnerw);
        startup.add(spinnerh);
        startup.add(submit);
        startup.validate();
        log = new Logger();
        this.add(startup);
        flag = 0;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setExtendedState(JFrame.NORMAL); 
        this.setResizable(false);
    }
    
    public void start(int w, int h) {
        popup = new JFrame();
        popup.setSize(100,500);
        popup.setLayout(null);
        popup.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        popup.setExtendedState(JFrame.NORMAL); 
        popup.setResizable(false);
        newEntityList = new JPanel();
        newEntityList.setLayout(null);
        newEntityList.setSize(100,500);
        species = new JButton[11];
        species[0] = new JButton("a");
        species[1] = new JButton("b");
        species[2] = new JButton("d");
        species[3] = new JButton("f");
        species[4] = new JButton("/");
        species[5] = new JButton("g");
        species[6] = new JButton("s");
        species[7] = new JButton("t");
        species[8] = new JButton("w");
        species[9] = new JButton("n");
        species[10] = new JButton("<-");
        for(int i = 0; i < 11; i++){
            species[i].setSize(80, 40);
            species[i].setLocation(10,i*40+10);
            species[i].addActionListener(this);
            species[i].setFocusable(false);
            newEntityList.add(species[i]);
        }
        newEntityList.setVisible(true);
        
        startup.setVisible(false);
        this.setSize(w*46, h*42+h/2+200);
        this.setLayout(null);
        boardPanel = new JPanel();
        menu = new JPanel();
        boardPanel.setLayout(new GridLayout(w,h));
        boardPanel.setBounds(0, 0, w*45, h*40);
        menu.setBounds(0, h*40, w*45, 200);
        boardPanel.setPreferredSize(new Dimension(w*45, h*40));
        menu.setLocation(0, h*40);
        menu.setPreferredSize(new Dimension(w*45, 200));
        popup.add(newEntityList);
        this.add(boardPanel);
        this.add(menu);
        logs = new JTextArea();
        DefaultCaret caret = (DefaultCaret)logs.getCaret();
        buttons = new JButton[w][h];
        for(int j = 0; j < h; j++){
            for(int i = 0; i < w; i++){
                buttons[i][j] = new JButton();
                buttons[i][j].setActionCommand(i+"x"+j);
                buttons[i][j].setBounds(i*45,j*40,45,40);
                buttons[i][j].setBackground(new Color(67,115,63));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll = new JScrollPane (logs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Dimension size = new Dimension(w*45, 200);
        scroll.setPreferredSize(size);
        menu.add(scroll);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setExtendedState(JFrame.NORMAL); 
        this.addKeyListener(this);
        requestFocus(true);
        this.setResizable(false);
        popup.setVisible(false);
    }
    
    public void addEntity(Entity entity){
        buttons[entity.getX()][entity.getY()].setIcon(entity.getIcon());
    }
    
    public void killEntity(Entity entity){
        buttons[entity.getX()][entity.getY()].setIcon(null);
    }
    
    public void moveEntity(Entity entity, int oldX, int oldY){
        buttons[oldX][oldY].setIcon(null);
        buttons[entity.getX()][entity.getY()].setIcon(entity.getIcon());
    }
    
    public void update(World world){
        for(int i = 0; i < world.getWidth(); i++){
            for(int j = 0; j < world.getHeight(); j++){
                if(world.getMap()[i][j] == ' '){
                    buttons[i][j].setIcon(null);
                } else {
                    for(int a = 0; a < world.getEntities().size(); a++){
                        if(world.getEntities().get(a).getName() == world.getMap()[i][j]){
                            buttons[i][j].setIcon(world.getEntities().get(a).getIcon());
                            break;
                        }
                    }
                }
            }
        }
        logs.setText(log.write());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT -> {
                direction = 0;
            }
            case KeyEvent.VK_RIGHT -> {
                direction = 1;
            }
            case KeyEvent.VK_UP -> {
                direction = 2;
            }
            case KeyEvent.VK_DOWN -> {
                direction = 3;
            }
            case KeyEvent.VK_S -> {
                flag = 1;
            }
            case KeyEvent.VK_L -> {
                flag = 2;
            }
            case KeyEvent.VK_P -> {
                flag = 3;
            }
        }
        System.out.println("pressed " + direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public int getDir(){
        System.out.print(" ");
        return direction;
    }
    
    public void setDir(int dir){
        direction = dir;
    }
    
    public Logger getLog(){
        return log;
    }
    
    public JTextArea getLogBox(){
        return logs;
    }
    
    public int getFlag(){
        return flag;
    }
    
    public void setFlag(){
        flag = 0;
    }
    
    public JButton getButton(int x, int y){
        return buttons[x][y];
    }
    
    public void newEntity(){
        
    }
    
    public int getStart(){
        System.out.print(" ");
        return start;
    }

    public int getW(){
        System.out.print(" ");
        return w;
    }
    
    public int getH(){
        System.out.print(" ");
        return h;
    }
    
    public char getN(){
        System.out.print(" ");
        return name;
    }
    
    public int getNEF(){
        System.out.print(" ");
        return newEntityFlag;
    }
    
    public void setNEF(){
        newEntityFlag = 0;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(start == 0){
            w = (int) spinnerw.getValue();
            h = (int) spinnerh.getValue();
            start = 1;
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        } else {
            if(newEntityFlag == 0){
                String s = e.getActionCommand();
                String[] ss = s.split("x");
                w = Integer.parseInt(ss[0]);
                h = Integer.parseInt(ss[1]);
                newEntityFlag = 1;
                popup.setVisible(true);
            } else {
                popup.setVisible(false);
                String s = e.getActionCommand();
                if("<-".equals(s)){
                    newEntityFlag = 0;
                } else {
                    name = s.charAt(0);
                    System.out.println("fnsdhfbnjsd");
                    newEntityFlag = 2;
                }
            }
        }
    }
}
