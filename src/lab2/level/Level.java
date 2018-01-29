package lab2.level;

import java.awt.Color;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class Level extends Observable {

    public Room currentRoom = null;
    boolean addToLevel = true;
    public ArrayList<Room> room_array = new ArrayList<Room>();

    public boolean place(Room r, int x, int y)  {
        if(addToLevel == false) {
            return false;
        }
        for( int i = 0; i < room_array.size(); i++) {
            if((x < room_array.get(i).levelCoordinatesX+room_array.get(i).dimensionX  && x > room_array.get(i).levelCoordinatesX
                    || (x + r.dimensionX < room_array.get(i).dimensionX+room_array.get(i).levelCoordinatesX && x + r.dimensionX > room_array.get(i).levelCoordinatesX)
                    || (x == room_array.get(i).levelCoordinatesX)
                    || x == room_array.get(i).dimensionX + room_array.get(i).levelCoordinatesX)) {
                System.out.println("Detta är x-call");
                if ((y+r.dimensionY >= room_array.get(i).levelCoordinatesY) && (y + r.dimensionY <= room_array.get(i).levelCoordinatesY + room_array.get(i).dimensionY)) {
                    System.out.println("That does not fit!");
                    return false;
                }
            }
        }
        r.levelCoordinatesX = x;
        r.levelCoordinatesY = y;
        room_array.add(r);
        return true;

    }

    //returnerar array med passande värden för leveln i x och y led.
    public int[] levelStorlek() {
        int maxX =0;
        int maxY =0;
        for(Room room: room_array) {
            maxX = Math.max(maxX, room.levelCoordinatesX + room.dimensionX);
            maxY = Math.max(maxY, room.levelCoordinatesY + room.dimensionY);
			/*if(room.levelCoordinatesX + room.dimensionX > maxX)
				maxX = room.levelCoordinatesX + room.dimensionX;
			if(room.levelCoordinatesY + room.dimensionY > maxY)
				maxY = room.levelCoordinatesY + room.dimensionY;*/
        }
        int[] max = {maxX,maxY};
        return max;

    }
    //ger ett rum status startrum och omöjliggör fler rum att placeras ut.
    public void firstLocation(Room r) {
        addToLevel = false;
        currentRoom = r;
    }
    public void update() {
        setChanged();
        notifyObservers();
    }

}