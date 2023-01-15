import exception.ItemUsableException;
import exception.MoveException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import repository.RepositoryItem;
import service.Combination;
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
        itens.add(new ItemUsable("chav", "utilizada para abir algo", 3, "vila",580,300,null));
        itens.add(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia", 200, 280, null));
    }

    @Test
    public void validarUsoDaChave(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        player.setCurrentMap(village);
        player.setPositionPlayerX(370);
        player.setPositionPlayerY(150);
        Use use = new Use(player,itens.get(0));
        assertTrue(use.run());
    }

    @Test (expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorItem(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        player.setCurrentMap(village);
        player.setPositionPlayerX(370);
        player.setPositionPlayerY(150);
        new Use(player,itens.get(3)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorDoor(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        village.setExitsDoors(templeDoor, temple);
        player.setCurrentMap(village);
        player.setPositionPlayerX(0);
        player.setPositionPlayerY(0);
        new Use(player,itens.get(0)).run();
    }

    @Test (expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorScenery(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        Door basementDoor = new Door(100,150,90,240,false);
        Room basement = new Room("porao do templo", null);
        Room topTemple = new Room("topo do templo", null);
        Door topTempleDoor = new Door(250,180,260,190,false);
        temple.setExitsDoors(templeDoor, village);
        temple.setExitsDoors(basementDoor,basement);
        temple.setExitsDoors(topTempleDoor,topTemple);
        player.setCurrentMap(temple);
        player.setPositionPlayerX(370);
        player.setPositionPlayerY(150);
        new Use(player,itens.get(0)).run();
    }

    @Test
    public void validarUsoDaEscada(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        Door basementDoor = new Door(100,150,90,240,false);
        Room basement = new Room("porao do templo", null);
        Room topTemple = new Room("topo do templo", null);
        Door topTempleDoor = new Door(250,180,260,190,false);
        temple.setExitsDoors(templeDoor, village);
        temple.setExitsDoors(basementDoor,basement);
        temple.setExitsDoors(topTempleDoor,topTemple);
        player.setCurrentMap(temple);
        player.setPositionPlayerX(250);
        player.setPositionPlayerY(180);
        Use use = new Use(player,itens.get(1));
        assertTrue(use.run());
    }

    @Test (expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorDoor(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        Door basementDoor = new Door(100,150,90,240,false);
        Room basement = new Room("porao do templo", null);
        Room topTemple = new Room("topo do templo", null);
        Door topTempleDoor = new Door(250,180,260,190,false);
        temple.setExitsDoors(templeDoor, village);
        temple.setExitsDoors(basementDoor,basement);
        temple.setExitsDoors(topTempleDoor,topTemple);
        player.setCurrentMap(temple);
        player.setPositionPlayerX(0);
        player.setPositionPlayerY(0);
        new Use(player,itens.get(1)).run();
    }

    @Test (expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorScenery(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        Door basementDoor = new Door(100,150,90,240,false);
        Room basement = new Room("porao do templo", null);
        Room topTemple = new Room("topo do templo", null);
        Door topTempleDoor = new Door(250,180,260,190,false);
        temple.setExitsDoors(templeDoor, village);
        temple.setExitsDoors(basementDoor,basement);
        temple.setExitsDoors(topTempleDoor,topTemple);
        player.setCurrentMap(village);
        player.setPositionPlayerX(250);
        player.setPositionPlayerY(180);
        new Use(player,itens.get(1)).run();
    }

    @Test (expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorItem(){
        Scenery village = new Scenery("vila", null);
        Door templeDoor = new Door(380, 530, 370, 150, false);
        Room temple = new Room("templo", null);
        Door basementDoor = new Door(100,150,90,240,false);
        Room basement = new Room("porao do templo", null);
        Room topTemple = new Room("topo do templo", null);
        Door topTempleDoor = new Door(250,180,260,190,false);
        temple.setExitsDoors(templeDoor, village);
        temple.setExitsDoors(basementDoor,basement);
        temple.setExitsDoors(topTempleDoor,topTemple);
        player.setCurrentMap(temple);
        player.setPositionPlayerX(250);
        player.setPositionPlayerY(180);
        new Use(player,itens.get(3)).run();
    }

    @Test
    public void validarUsoDaPa(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }

        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        ArrayList<Item> item = new ArrayList<>();
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, null));
        new Combination(player, item).run();

        Use use = new Use(player,itens.get(4));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItem(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        ArrayList<Item> item = new ArrayList<>();
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, null));
        new Combination(player, item).run();

        new Use(player,itens.get(3)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorInventario(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }

        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        new Use(player,itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItensInvisiveis(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }
        Scenery beach = new Scenery("praia", null);
        player.setCurrentMap(beach);

        //mapa visivel
        ArrayList<Item> item = new ArrayList<>();
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, null));
        new Combination(player, item).run();

        new Use(player,itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorScenery(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery());

        //mapa visivel
        ArrayList<Item> item = new ArrayList<>();
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, null));
        new Combination(player, item).run();

        new Use(player,itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void validarItemNaoUsavel(){
        new Use(player,itens.get(2)).run();
    }

}
