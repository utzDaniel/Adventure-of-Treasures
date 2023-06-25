import backend.service.exception.ItemUsableException;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemCombinableBuilder;
import backend.service.model.builder.ItemMissionBuilder;
import backend.service.model.builder.ItemUsableBuilder;
import backend.service.model.Player;
import backend.service.model.builder.Room;
import backend.service.model.builder.Scenery;
import backend.service.interfaces.ICombinable;
import backend.service.interfaces.IUsable;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.component.Combination;
import backend.service.component.Use;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UseTest {

    private final Player player = Player.getInstance();
    private final List<Item> itens = new ArrayList<>();

    @Before
    public void inicial() {
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chave").description("utilizada para abir algo").weight(0)
                .coordinate(ICoordinate.getInstance(580, 300)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("templo").name("escada").description("utilizada para abir algo").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chav").description("utilizada para abir algo").weight(0)
                .coordinate(ICoordinate.getInstance(580, 300)).image(null).removable(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).build());
    }

    @Test
    public void validarUsoDaChave() {
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(370, 150));
        Use use = new Use((IUsable) itens.get(0));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorItem() {
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(370, 150));
        new Use((IUsable) itens.get(2)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorDoor() {
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(0, 0));
        new Use((IUsable) itens.get(0)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaChavePorScenery() {
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        player.setCurrentMap(temple);
        player.setLocation(ICoordinate.getInstance(370, 150));
        new Use((IUsable) itens.get(0)).run();
    }

    @Test
    public void validarUsoDaEscada() {
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        player.setLocation(ICoordinate.getInstance(260, 190));
        player.setCurrentMap(temple);
        Use use = new Use((IUsable) itens.get(1));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorDoor() {
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        player.setCurrentMap(temple);
        player.setLocation(ICoordinate.getInstance(0, 0));
        new Use((IUsable) itens.get(1)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorScenery() {
        Scenery village = (Scenery) RepositoryFactory.getRepositoryMapGame().get("vila");
        player.setCurrentMap(village);
        player.setLocation(ICoordinate.getInstance(250, 180));
        new Use((IUsable) itens.get(1)).run();
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaEscadaPorItem() {
        Room temple = (Room) RepositoryFactory.getRepositoryMapGame().get("templo");
        player.setCurrentMap(temple);
        player.setLocation(ICoordinate.getInstance(250, 180));
        new Use((IUsable) itens.get(2)).run();
    }

    @Test
    public void validarUsoDaPa() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(((Scenery)repositoryMapGame.get("cais")).getExit("leste"));

        //mapa visivel
        var itens = new ArrayList<Item>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(ICoordinate.getInstance(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(ICoordinate.getInstance(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);

        var iCombinableList = itens.stream()
                .map(item1 -> (ICombinable) item1).toList();
        new Combination(iCombinableList).run();

        Use use = new Use((IUsable) this.itens.get(3));
        assertTrue(use.run());
    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorItem() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(((Scenery)repositoryMapGame.get("cais")).getExit("leste"));

        //mapa visivel
        var itens = new ArrayList<Item>();

        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(ICoordinate.getInstance(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(ICoordinate.getInstance(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);

        var iCombinableList = itens.stream()
                .map(item1 -> (ICombinable) item1).toList();
        new Combination(iCombinableList).run();

        new Use((IUsable) this.itens.get(2)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorInventario() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);
        player.setCurrentMap(((Scenery)repositoryMapGame.get("cais")).getExit("leste"));

        new Use((IUsable) itens.get(3)).run();

    }

    @Test(expected = ItemUsableException.class)
    public void invalidarUsoDaPaPorScenery() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        player.setCurrentMap(repositoryMapGame.get("cais"));

        //mapa visivel
        var itens = new ArrayList<Item>();
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(ICoordinate.getInstance(510, 320)).image(null).removable(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(ICoordinate.getInstance(490, 390)).image(null).removable(true).build());
        Item item = ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(false).visible(false).build();
        player.getInventory().setItemInvisible(item);

        var iCombinableList = itens.stream()
                .map(item1 -> (ICombinable) item1).toList();
        new Combination(iCombinableList).run();

        new Use((IUsable) this.itens.get(3)).run();

    }
}
