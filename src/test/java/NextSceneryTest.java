import backend.service.model.Coordinate;
import backend.service.model.Player;
import backend.service.model.builder.MapGame;
import backend.repository.factory.RepositoryFactory;
import frontend.model.component.ComponentFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import backend.service.enums.Direction;
import backend.service.move.NextScenery;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NextSceneryTest {

    private Player player;
    private NextScenery nextScenery;

    @Before
    public void inicialize() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(repositoryMapGame.get("cais"));
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(player));
        listJLabel.add(ComponentFactory.getJLabel(player.getCurrentMap()));
        nextScenery = new NextScenery(new Coordinate(810,662));
    }

    @Test
    public void testSceneryNull(){
        MapGame atualMap = player.getCurrentMap();
        nextScenery.run("sul");
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionNorte(){
        MapGame atualMap = player.getCurrentMap();
        nextScenery.run("norte");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionLeste(){
        MapGame atualMap = player.getCurrentMap();
        nextScenery.run("leste");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionOeste(){
        MapGame atualMap = player.getCurrentMap();
        nextScenery.run("oeste");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testEntrarDoorESair(){
        MapGame atualMap = player.getCurrentMap();
        nextScenery.run("norte");
        nextScenery.run("sul");
        assertEquals(atualMap, player.getCurrentMap());
    }
}
