import exception.ButtonException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import view.ButtonAction;
import view.InterfaceGame;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InterfaceGameTest {

    private InterfaceGame interfaceGame;

    @Before
    public void create(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        interfaceGame = new InterfaceGame(player.getCurrentMap().getImagemIcon());
    }

   /* @Test
    public void createAllButtonValid(){
        System.out.println(interfaceGame.getFrame());
        System.out.println(interfaceGame.getFrame()
                .getContentPane().getComponent(1));

        //assertEquals(button.length,buttonAction.getButtonActions().length);
    }*/


}
