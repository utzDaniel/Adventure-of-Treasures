import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Door;
import backend.service.model.Player;
import backend.service.model.builder.MapGame;
import backend.service.model.builder.Room;
import backend.service.model.builder.Scenery;
import frontend.model.component.ComponentFactory;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NextDoorTest {

    private Player player;
    private Door templeDoor;
    private Door vilaDoor;
    private OpenDoor nextDoor;

    @Before
    public void inicialize() {
        player = Player.getInstance();
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        templeDoor = temple.getDoor(ICoordinate.getInstance(380, 530)).get();
        vilaDoor = village.getDoor(ICoordinate.getInstance(370, 150)).get();
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(370, 150));
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(this.player.getIcon().toString(), this.player.getLocation())));
        listJLabel.add(ComponentFactory.getJLabel("mapa", this.player.getCurrentMap().getIcon().toString()));
        //nextDoor = new NextDoor(new InterfaceGame(listJLabel));
        nextDoor = new OpenDoor(null);
    }

    @Test
    public void testDoorNull() {
        player.setLocation(ICoordinate.getInstance(350, player.getLocation().y()));
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test//MapGameException.class
    public void testDoorFechada() {
        vilaDoor.setOpen(false);
        templeDoor.setOpen(false);
        player.setLocation(ICoordinate.getInstance(370, 150));
        nextDoor.run();
    }

    @Test
    public void testDoorAberta() {
        vilaDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        player.setLocation(ICoordinate.getInstance(370, 150));
        nextDoor.run();
        assertNotEquals(atualMap.getName(), player.getCurrentMap().getName());
    }

    @Test
    public void testEntrarDoorESair() {
        vilaDoor.setOpen(true);
        templeDoor.setOpen(true);
        MapGame atualMap = player.getCurrentMap();
        nextDoor.run();
        nextDoor.run();
        assertEquals(atualMap, player.getCurrentMap());
    }
}
