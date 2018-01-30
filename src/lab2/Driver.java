package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

    /*level.firstLocation(level.room_array.get(0));
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
      level.room_array.get(5).connectNorthTo(level.room_array.get(2));*/

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

        level.firstLocation(level.room_array.get(0));
        createConnection(level, 0, 1, "west");
        createConnection(level,0,4,"south");
        createConnection(level,1,2,"south");
        createConnection(level,2,5,"south");
        createConnection(level,2,1,"north");
        createConnection(level,2,4,"east");
        createConnection(level,3,4,"north");
        createConnection(level,3,5,"west");
        createConnection(level,4,2,"west");
        createConnection(level,4,3,"south");
        createConnection(level,5,3,"east");
        createConnection(level,5,2,"north");
        //Ändra startrum genom att byta index för arrayen.

    }
    private void createConnection(Level level, int room1, int room2, String side) {
        try{
            switch (side) {
                case "west":
                    level.room_array.get(room1).connectWestTo(level.room_array.get(room2));
                    break;
                case "north":
                    level.room_array.get(room1).connectNorthTo(level.room_array.get(room2));
                    break;
                case "east":
                    level.room_array.get(room1).connectEastTo(level.room_array.get(room2));
                    break;
                case "south":
                    level.room_array.get(room1).connectSouthTo(level.room_array.get(room2));
                    break;
                default:
                    System.out.println("error ditt fel");
                    break;
            }
        }   catch (IndexOutOfBoundsException e){
                System.out.println("That room didn't fit");
        }
    }
}