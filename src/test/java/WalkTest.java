import exception.MoveException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class WalkTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
    }

    @Test
    public void andarPlayerParaLeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.LESTE.getLabel(), new JLabel());
        assertEquals(positionX+10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.OESTE.getLabel(), new JLabel());
        assertEquals(positionX-10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.SUL.getLabel(), new JLabel());
        assertEquals(positionY+10, player.getPositionPlayerY());
    }
    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.NORTE.getLabel(), new JLabel());
        assertEquals(positionY-10, player.getPositionPlayerY());
    }

    @Test (expected = MoveException.class)
    public void naoAndarPlayerParaNort() {
        int positionY = player.getPositionPlayerY();
        player.walk("nort", new JLabel());
        assertEquals(positionY-10, player.getPositionPlayerY());
    }

    @Test
    public void naoAndarPlayer() {
        player.setPositionPlayerX(230,new JLabel());
        player.setPositionPlayerY(530,new JLabel());
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.SUL.getLabel(), new JLabel());
        assertEquals(positionY, player.getPositionPlayerY());
    }

}
