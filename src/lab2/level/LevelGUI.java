package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("deprecation")
public class LevelGUI implements Observer {

    private Level lv;
    private Display d;
    public LevelGUI(Level level, String name) {

        //vid körning det dynamiska objektet som arbetas på av LevelGUI i stunden
        this.lv = level;

        //kallar på addObserver i objektet vi arbetar på i stunden, på this,
        //alltså LevelGUI. Vilket gör detta till accepterad observer
        this.lv.addObserver(this);
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: You should change 200 to a value
        // depending on the size of the level
        d = new Display(lv,500,500);

        frame.getContentPane().add(d);
        frame.pack();
        frame.setLocation(100,100);
        frame.setVisible(true);
    }

    //"målar om" då update blir kallad på.
    public void update(Observable arg0, Object arg1) {
        d.repaint();
    }

    private class Display extends JPanel {

        public Display(Level fp, int x, int y) {

            addKeyListener(new Listener());

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(lv.levelStorlek()[0]+20,lv.levelStorlek()[1]+20));
            setFocusable(true);
        }
        //målar ut alla dörrar till olika rum.

        public void paintConnect(Graphics g) {
            g.setColor(Color.BLACK);
            //"extendar" graphics klassen så vi kan använda metoder i den.
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke (2));
            //Ritar sträck från rätt "dörr" till rätt rum.
            for(Room room: lv.room_array) {
                if(room.northWall != null) {
                    g.drawLine(room.levelCoordinatesX+50, room.levelCoordinatesY, room.northWall.levelCoordinatesX+50, room.northWall.levelCoordinatesY+50);
                }
                if(room.eastWall != null) {
                    g.drawLine(room.levelCoordinatesX+100, room.levelCoordinatesY + 50, room.eastWall.levelCoordinatesX+50, room.eastWall.levelCoordinatesY+50);
                }
                if(room.westWall != null) {
                    g.drawLine(room.levelCoordinatesX, room.levelCoordinatesY + 50, room.westWall.levelCoordinatesX+50, room.westWall.levelCoordinatesY+50);
                }
                if(room.southWall != null) {
                    g.drawLine(room.levelCoordinatesX+50, room.levelCoordinatesY+100, room.southWall.levelCoordinatesX+50, room.southWall.levelCoordinatesY+50);
                }
            }
        }

        //Ritar ut leveln med informationen vi fått, loopar genom rummen som ej har överlappat
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(Room room: lv.room_array) {
                g.setColor(room.paintJob);
                g.fillRect(room.levelCoordinatesX, room.levelCoordinatesY, room.dimensionX, room.dimensionY);
                if(room == lv.currentRoom) {
                    //ger startrummet en extra border så vi kan se vilket som vi startar i, samt vilket som är aktuella rummet
                    g.setColor(new Color(90,90,90));
                    Graphics2D g2 = (Graphics2D) g;
                    //för att spara strokevärdet
                    Stroke oldStroke = g2.getStroke();
                    g2.setStroke(new BasicStroke(6));
                    g2.drawRect(lv.currentRoom.levelCoordinatesX, lv.currentRoom.levelCoordinatesY, lv.currentRoom.dimensionX, lv.currentRoom.dimensionY);
                }
            }
            //kallar på paintConnect för att rita kopplingarna.
            paintConnect(g);
        }

        private class Listener implements KeyListener {

            public void keyPressed(KeyEvent arg0) {
            }
            //Vid släpp av knapp går spelaren åt rätt håll till rätt rum, ifall vägen finns.
            public void keyReleased(KeyEvent arg0) {
                if(arg0.getKeyCode() == KeyEvent.VK_A) {
                    if(lv.currentRoom.westWall != null) {
                        lv.currentRoom = lv.currentRoom.westWall;
                        //System.out.println("A pressed");
                    }
                } else if(arg0.getKeyCode() == KeyEvent.VK_W) {
                    if(lv.currentRoom.northWall != null) {
                        lv.currentRoom = lv.currentRoom.northWall;
                    }
                } else if(arg0.getKeyCode() == KeyEvent.VK_D) {
                    if(lv.currentRoom.eastWall != null) {
                        lv.currentRoom = lv.currentRoom.eastWall;
                    }
                } else if(arg0.getKeyCode() == KeyEvent.VK_S) {
                    if(lv.currentRoom.southWall != null) {
                        lv.currentRoom = lv.currentRoom.southWall;
                    }
                }
                //updaterar och kallar på setchanged, notifyObserver i level.
                lv.update();
            }

            public void keyTyped(KeyEvent event) {
            }
        }
    }
}