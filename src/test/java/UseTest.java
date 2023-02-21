import exception.ItemUsableException;
import model.Coordinate;
import model.Player;
import model.builder.item.*;
import model.builder.map.Room;
import model.builder.map.Scenery;
import model.interfaces.IUsable;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import service.Combination;
import service.Use;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UseTest {

    private final Player player = Player.getInstance();
    private final List<Item> itens = new ArrayList<>();

    @Before
    public void inicial() {
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chave").description("utilizada para abir algo").weight(0)
                .coordinate(new Coordinate(580, 300)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("templo").name("escada").description("utilizada para abir algo").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("tocha").description("utilizado para iluminar").weight(0)
                .coordinate(new Coordinate(410, 220)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chav").description("utilizada para abir algo").weight(0)
                .coordinate(new Coordinate(580, 300)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(new Coordinate(200, 280)).image(null).removable(true).build());
    }

    @Test
    public void validarUsoDaChave() {
        Scenery village = (Scenery) RepositoryMapGame.getInstance().getMapGame("vila");
        player.setCurrentMap(village);
        player.setLocation(new Coordinate(370, 150));
        Use use = new Use((IUsable) itens.get(0));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorItem() {
        Scenery village = (Scenery) RepositoryMapGame.getInstance().getMapGame("vila");
        player.setCurrentMap(village);
        player.setLocation(new Coordinate(370, 150));
        new Use((IUsable) itens.get(3)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorDoor() {
        Scenery village = (Scenery) RepositoryMapGame.getInstance().getMapGame("vila");
        player.setCurrentMap(village);
        player.setLocation(new Coordinate(0, 0));
        new Use((IUsable) itens.get(0)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorScenery() {
        Room temple = (Room) RepositoryMapGame.getInstance().getMapGame("templo");
        player.setCurrentMap(temple);
        player.setLocation(new Coordinate(370, 150));
        new Use((IUsable) itens.get(0)).run();
    }

    @Test
    public void validarUsoDaEscada() {
        Room temple = (Room) RepositoryMapGame.getInstance().getMapGame("templo");
        player.setLocation(new Coordinate(260, 190));
        player.setCurrentMap(temple);
        Use use = new Use((IUsable) itens.get(1));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorDoor() {
        Room temple = (Room) RepositoryMapGame.getInstance().getMapGame("templo");
        player.setCurrentMap(temple);
        player.setLocation(new Coordinate(0, 0));
        new Use((IUsable) itens.get(1)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorScenery() {
        Scenery village = (Scenery) RepositoryMapGame.getInstance().getMapGame("vila");
        player.setCurrentMap(village);
        player.setLocation(new Coordinate(250, 180));
        new Use((IUsable) itens.get(1)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorItem() {
        Room temple = (Room) RepositoryMapGame.getInstance().getMapGame("templo");
        player.setCurrentMap(temple);
        player.setLocation(new Coordinate(250, 180));
        new Use((IUsable) itens.get(3)).run();
    }

    @Test
    public void validarUsoDaPa() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        var itens = new ArrayList<Item>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(new Coordinate(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(new Coordinate(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);
        new Combination(itens).run();

        Use use = new Use((IUsable) this.itens.get(4));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItem() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        //mapa visivel
        var itens = new ArrayList<Item>();

        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(new Coordinate(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(new Coordinate(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);

        new Combination(itens).run();

        new Use((IUsable) this.itens.get(3)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorInventario() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);
        player.setCurrentMap(createMapGame.getInitialScenery().getExit("leste"));

        new Use((IUsable) itens.get(4)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorScenery() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(createMapGame.getInitialScenery());

        //mapa visivel
        var itens = new ArrayList<Item>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(new Coordinate(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(new Coordinate(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);

        new Combination(itens).run();

        new Use((IUsable) this.itens.get(4)).run();

    }
}
