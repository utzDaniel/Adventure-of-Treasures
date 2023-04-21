import frontend.model.component.JLabelFactory;
import rules.exception.MapGameException;
import backend.model.Door;
import backend.model.Player;
import backend.model.builder.map.MapGame;
import backend.model.builder.map.Room;
import backend.model.builder.map.Scenery;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;
import rules.service.NextDoor;
import frontend.view.InterfaceGame;

import javax.swing.*;
import java.util.ArrayList;

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
        templeDoor = temple.getDoor(ICoordinate.getInstance(380,530)).get();
        vilaDoor = village.getDoor(ICoordinate.getInstance(370,150)).get();
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(370,150));
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(JLabelFactory.getInstance(player));
        listJLabel.add(JLabelFactory.getInstance(player.getCurrentMap()));
        nextDoor = new NextDoor(new InterfaceGame(listJLabel));
    }

    @Test
    public void testDoorNull(){
        player.setLocation(ICoordinate.getInstance(350, player.getLocation().getY()));
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test (expected = MapGameException.class)
    public void testDoorFechada(){
        vilaDoor.setOpen(false);
        templeDoor.setOpen(false);
        player.setLocation(ICoordinate.getInstance(370,150));
        nextDoor.run();
    }

    @Test
    public void testDoorAberta(){
        vilaDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        player.setLocation(ICoordinate.getInstance(370,150));
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
