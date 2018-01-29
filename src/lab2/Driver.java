package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

    public void run() {
        //skapar refvariabeln level en instans av Level.
        Level level = new Level();

        //placerar nya Room-objekt i level via place funktionen som också kollar så den ej kolliderar med tidigare placerade rum.
        level.place(new Room(100,100,Color.blue), 500, 80);
        level.place(new Room(100,100,Color.green),50,50);
        level.place(new Room(100,100,Color.CYAN),180,180);
        level.place(new Room(100,100, Color.red),200,400);
        level.place(new Room(100,100,Color.magenta),301,200);
        level.place(new Room(100,100,Color.yellow),50,350);
        //level.place(new Room(100,100,Color.gray), 500, 300);

        //skapar ett LevelGUI
        LevelGUI newlvl = new LevelGUI(level, "fisk");

        //Ändra startrum genom att byta index för arrayen.
        level.firstLocation(level.room_array.get(0));
        level.room_array.get(0).connectWestTo(level.room_array.get(1));
        level.room_array.get(0).connectSouthTo(level.room_array.get(4));
        level.room_array.get(1).connectSouthTo(level.room_array.get(2));
        level.room_array.get(2).connectSouthTo(level.room_array.get(5));
        level.room_array.get(2).connectNorthTo(level.room_array.get(1));
        level.room_array.get(2).connectEastTo(level.room_array.get(4));
        level.room_array.get(3).connectNorthTo(level.room_array.get(4));
        level.room_array.get(3).connectWestTo(level.room_array.get(5));
        level.room_array.get(4).connectWestTo(level.room_array.get(2));
        level.room_array.get(4).connectSouthTo(level.room_array.get(3));
        level.room_array.get(5).connectEastTo(level.room_array.get(3));
        level.room_array.get(5).connectNorthTo(level.room_array.get(2));
    }
}