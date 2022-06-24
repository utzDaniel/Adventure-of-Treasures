import model.*;
import org.junit.Before;
import org.junit.Test;
import service.Use;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UseTest {

    private final Player player = new Player();
    private final List<Item> itens = new ArrayList<>();

    @Before
    public void inicial(){
        itens.add(new ItemUsable("chave", "utilizada para abir algo", 3, "vila",580,300,null));
        itens.add(new ItemUsable("escada", "utilizada para subir em algum lugar", 5, "templo",410,200,null));
        itens.add(new ItemEquipable("tocha", "utilizado para iluminar", 5,410,200,null));
    }

    @Test
    public void validarUsoDaChave(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        player.setCurrentMap(village);
        player.setPositionPlayerX(370,new JLabel());
        player.setPositionPlayerY(150,new JLabel());
        Use use = new Use(player,itens.get(0));
        assertTrue(use.run());
    }

    @Test
    public void validarItemNaoUsavel(){
        Use use = new Use(player,itens.get(2));
        assertFalse(use.run());
    }

    @Test
    public void validarItemUsavelMasNoMapaErrado(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        player.setCurrentMap(temple);
        player.setPositionPlayerX(370,new JLabel());
        player.setPositionPlayerY(150,new JLabel());
        Use use = new Use(player,itens.get(0));
        assertFalse(use.run());
    }

}
