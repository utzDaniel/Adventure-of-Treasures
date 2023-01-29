import model.enums.Direction;
import model.builder.map.MapGame;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import repository.RepositoryMapGame;
import service.NextScenery;
import view.InterfaceGame;

import static org.junit.Assert.assertEquals;

public class NextSceneryTest {

    private Player player;
    private NextScenery nextScenery;

    @Before
    public void inicialize() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        nextScenery = new NextScenery(new InterfaceGame());
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
