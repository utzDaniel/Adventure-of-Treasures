import frontend.model.component.JLabelFactory;
import rules.enums.Direction;
import backend.model.builder.map.MapGame;
import backend.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import backend.repository.RepositoryFactory;
import rules.service.NextScenery;
import frontend.view.InterfaceGame;

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
        listJLabel.add(JLabelFactory.getInstance(player));
        listJLabel.add(JLabelFactory.getInstance(player.getCurrentMap()));
        nextScenery = new NextScenery(new InterfaceGame(listJLabel));
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
