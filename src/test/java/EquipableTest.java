import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemEquipable;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.repository.factory.RepositoryFactory;
import org.junit.Before;
import org.junit.Test;
import backend.controller.exception.ItemEquipableException;
import backend.service.interfaces.ICoordinate;
import backend.service.component.Equip;
import backend.service.component.Unequip;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EquipableTest {
    private final ArrayList<Item> itens = new ArrayList<>();

    @Before
    public void inicial() {
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("toch").description("utilizado para iluminar").weight(0)
                .coordinate(ICoordinate.getInstance(410, 220)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("tocha").description("utilizado para iluminar").weight(0)
                .coordinate(ICoordinate.getInstance(410, 220)).image(null).removable(true).visible(true).build());
    }

    @Test
    public void validarItemEquipMochila() {
        Player player = Player.getInstance();
        new AddItemInventory(player.getInventory(), itens.get(0)).run();
        ((ItemEquipable) itens.get(0)).equip();
        assertTrue(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemEquipMochilaAtributo() {
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        new AddItemInventory(player.getInventory(), itens.get(0)).run();
        ((ItemEquipable) itens.get(0)).equip();
        assertEquals(capacity + 5, player.getInventory().getMaxCapacity());
    }

    @Test
    public void validarItemUnequipMochila() {
        Player player = Player.getInstance();
        new AddItemInventory(player.getInventory(), itens.get(0)).run();
        ((ItemEquipable) itens.get(0)).equip();
        ((ItemEquipable) itens.get(0)).unequip();
        assertFalse(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemUnequipMochilaAtributo() {
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        new AddItemInventory(player.getInventory(), itens.get(0)).run();
        ((ItemEquipable) itens.get(0)).equip();
        ((ItemEquipable) itens.get(0)).unequip();
        assertEquals(capacity, player.getInventory().getMaxCapacity());
    }

    @Test(expected = ItemEquipableException.class)
    public void validarItemUnequipMochilaAtributoErro() {
        Player player = Player.getInstance();
        new AddItemInventory(player.getInventory(), itens.get(0)).run();
        ((ItemEquipable) itens.get(0)).equip();
        int size = player.getInventory().getMaxCapacity() - player.getInventory().getCapacity();

        var item = ItemEquipableBuilder.builder().equipped(false).name("peso").description("pesar").weight(size)
                .coordinate(ICoordinate.getInstance(410, 220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item).run();
        ((ItemEquipable) itens.get(0)).unequip();
    }

    @Test
    public void validarItemEquipTocha() {
        Player player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        new AddItemInventory(player.getInventory(), itens.get(2)).run();
        ((ItemEquipable) itens.get(2)).equip();
        assertTrue(((ItemEquipable) itens.get(2)).isEquipped());
    }

    @Test
    public void validarItemEquipTochaAtributo() {
        Player player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        new AddItemInventory(player.getInventory(), itens.get(2)).run();
        assertTrue(((ItemEquipable) itens.get(2)).equip());
    }

    @Test
    public void validarItemUnequipTocha() {
        Player player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        new AddItemInventory(player.getInventory(), itens.get(2)).run();
        ((ItemEquipable) itens.get(2)).equip();
        ((ItemEquipable) itens.get(2)).unequip();
        assertFalse(((ItemEquipable) itens.get(2)).isEquipped());
    }

    @Test
    public void validarItemUnequipTochaAtributo() {
        Player player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        new AddItemInventory(player.getInventory(), itens.get(2)).run();
        ((ItemEquipable) itens.get(2)).equip();
        assertTrue(((ItemEquipable) itens.get(2)).unequip());
    }

    @Test
    public void validarItemEquip() {
        assertTrue(new Equip((ItemEquipable) itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void itemEquipInvalido() {
        assertFalse(new Equip((ItemEquipable) itens.get(1)).run());
    }


    @Test
    public void validarItemUnequip() {
        assertTrue(new Unequip((ItemEquipable) itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void itemUnequipInvalido() {
        assertFalse(new Unequip((ItemEquipable) itens.get(1)).run());
    }
}
