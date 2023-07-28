import backend.service.enums.Move;
import backend.service.model.Player;
import backend.service.model.builder.MapGame;
import backend.repository.factory.RepositoryFactory;
import frontend.model.component.ComponentFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import backend.service.component.move.MovePlayerNextScenery;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NextSceneryTest {

    private Player player;
    private MovePlayerNextScenery moveScenery;

    @Before
    public void inicialize() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setM(Move.SUL);
        player.setCurrentMap(repositoryMapGame.get("cais"));
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(this.player.getIcon().toString(), this.player.getLocation())));
        listJLabel.add(ComponentFactory.getJLabel("mapa", this.player.getCurrentMap().getIcon().toString()));
        moveScenery = new MovePlayerNextScenery(player, Move.SUL);
    }

    @Test
    public void testSceneryNull(){
        MapGame atualMap = player.getCurrentMap();
        moveScenery.run("sul");
        assertEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionNorte(){
        MapGame atualMap = player.getCurrentMap();
        moveScenery.run("norte");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionLeste(){
        MapGame atualMap = player.getCurrentMap();
        moveScenery.run("leste");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testSceneryDirectionOeste(){
        MapGame atualMap = player.getCurrentMap();
        moveScenery.run("oeste");
        Assertions.assertNotEquals(atualMap, player.getCurrentMap());
    }

    @Test
    public void testEntrarDoorESair(){
        MapGame atualMap = player.getCurrentMap();
        moveScenery.run("norte");
        moveScenery.run("sul");
        assertEquals(atualMap, player.getCurrentMap());
    }
}
