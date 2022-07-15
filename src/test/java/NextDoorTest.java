import exception.MapGameException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import service.NextDoor;
import view.InterfaceGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class NextDoorTest {

    private Player player;
    private Door templeDoor;
    private NextDoor nextDoor;

    @Before
    public void inicialize() {
        player = new Player();
        Scenery village = new Scenery("vila", null);
        templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        temple.setExitsDoors(templeDoor, village);
        player.setCurrentMap(village);
        player.setPositionPlayerX(370,new JLabel());
        player.setPositionPlayerY(150,new JLabel());
        nextDoor = new NextDoor(player, new InterfaceGame(null));
    }

    @Test
    public void testDoorNull(){
        player.setPositionPlayerX(350,new JLabel());
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test (expected = MapGameException.class)
    public void testDoorFechada(){
        nextDoor.run();
    }

    @Test
    public void testDoorAberta(){
        templeDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testEntrarDoorESair(){
        templeDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }
}
