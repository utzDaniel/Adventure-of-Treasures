import exception.MapGameException;
import model.*;
import model.builder.map.MapGame;
import model.builder.map.Room;
import model.builder.map.Scenery;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import service.NextDoor;
import view.InterfaceGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NextDoorTest {

    private Player player;
    private Door templeDoor;
    private Door vilaDoor;
    private NextDoor nextDoor;

    @Before
    public void inicialize() {
        player = Player.getInstance();
        Scenery village = (Scenery) RepositoryMapGame.getInstance().getMapGame("vila");
        Room temple = (Room) RepositoryMapGame.getInstance().getMapGame("templo");
        //templeDoor = new Door(380, 530, 370, 150, false);
        templeDoor = temple.getDoor(380,530).get();
        vilaDoor = village.getDoor(370,150).get();
        player.setCurrentMap(village);
        player.setPositionPlayerX(370);
        player.setPositionPlayerY(150);
        nextDoor = new NextDoor(new InterfaceGame());
    }

    @Test
    public void testDoorNull(){
        player.setPositionPlayerX(350);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test (expected = MapGameException.class)
    public void testDoorFechada(){
        vilaDoor.setOpen(false);
        templeDoor.setOpen(false);
        nextDoor.run();
    }

    @Test
    public void testDoorAberta(){
        vilaDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertNotEquals(atualMap.getName(), player.getCurrentMap().getName());
    }

    @Test
    public void testEntrarDoorESair(){
        vilaDoor.setOpen(true);
        templeDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }
}
