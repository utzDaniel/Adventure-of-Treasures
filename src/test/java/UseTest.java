import exception.ItemUsableException;
import model.*;
import model.builder.item.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import service.Combination;
import service.Use;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UseTest {

    private final Player player = Player.getInstance();
    private final List<Item> itens = new ArrayList<>();

    @Before
    public void inicial(){
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chave").description("utilizada para abir algo").weight(0)
                .positionX(580).positionY(300).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("templo").name("escada").description("utilizada para abir algo").weight(0)
                .positionX(410).positionY(200).image(null).removable(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("tocha").description("utilizado para iluminar").weight(0)
                .positionX(410).positionY(220).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chav").description("utilizada para abir algo").weight(0)
                .positionX(580).positionY(300).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .positionX(200).positionY(280).image(null).removable(true).build());
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
        Use use = new Use(itens.get(0));
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
        new Use(itens.get(3)).run();
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
        new Use(itens.get(0)).run();
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
        new Use(itens.get(0)).run();
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
        Use use = new Use(itens.get(1));
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
        new Use(itens.get(1)).run();
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
        new Use(itens.get(1)).run();
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
        new Use(itens.get(3)).run();
    }

    @Test
    public void validarUsoDaPa(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }

        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).build());

        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .positionX(490).positionY(390).image(null).removable(true).build());

        new Combination(itens).run();

        Use use = new Use(this.itens.get(4));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItem(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        ArrayList<Item> itens = new ArrayList<>();

        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).build());

        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .positionX(490).positionY(390).image(null).removable(true).build());

        new Combination(itens).run();

        new Use(this.itens.get(3)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorInventario(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }

        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        new Use(itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItensInvisiveis(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        Scenery beach = new Scenery("praia", null);
        player.setCurrentMap(beach);

        //mapa visivel
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).build());

        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .positionX(490).positionY(390).image(null).removable(true).build());
        new Combination(itens).run();

        new Use(this.itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorScenery(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery());

        //mapa visivel
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).build());

        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .positionX(490).positionY(390).image(null).removable(true).build());
        new Combination(itens).run();

        new Use(this.itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void validarItemNaoUsavel(){
        new Use(itens.get(2)).run();
    }

}
