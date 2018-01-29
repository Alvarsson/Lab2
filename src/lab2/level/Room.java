package lab2.level;

import java.awt.Color;
import java.sql.Driver;


public class Room {
    Room northWall;
    Room eastWall;
    Room southWall;
    Room westWall;
    int dimensionX;
    int dimensionY;
    Color paintJob;
    int levelCoordinatesX;
    int levelCoordinatesY;


    public Room(int dx, int dy, Color color) {
        northWall = null;
        eastWall = null;
        southWall = null;
        westWall = null;
        dimensionX = dx;
        dimensionY = dy;
        paintJob = color;
        System.out.print("Dx: "+ dimensionX +" Dy: " + dimensionY + " ");
        System.out.println("Color: "+ paintJob);
    }

    public void connectNorthTo(Room r) {
        northWall = r;
    }
    public void connectEastTo(Room r) {
        eastWall = r;
    }
    public void connectSouthTo(Room r) {
        southWall = r;
    }
    public void connectWestTo(Room r) {
        westWall = r;
    }
}