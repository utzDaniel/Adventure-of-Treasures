import exception.MapGameException;
import model.Coordinate;
import model.Door;
import model.Player;
import model.builder.map.MapGame;
import model.builder.map.Room;
import model.builder.map.Scenery;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryFactory;
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
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        templeDoor = temple.getDoor(new Coordinate(380,530)).get();
        vilaDoor = village.getDoor(new Coordinate(370,150)).get();
        player.setCurrentMap(village);
        player.setLocation(new Coordinate(370,150));
        nextDoor = new NextDoor(new InterfaceGame());
    }

    @Test
    public void testDoorNull(){
        player.setLocation(new Coordinate(350, player.getLocation().getY()));
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test (expected = MapGameException.class)
    public void testDoorFechada(){
        vilaDoor.setOpen(false);
        templeDoor.setOpen(false);
        player.setLocation(new Coordinate(370,150));
        nextDoor.run();
    }

    @Test
    public void testDoorAberta(){
        vilaDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        player.setLocation(new Coordinate(370,150));
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
