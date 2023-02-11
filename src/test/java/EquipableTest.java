import exception.ItemEquipableException;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemEquipable;
import model.builder.item.ItemEquipableBuilder;
import model.builder.item.ItemUsableBuilder;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import service.Equip;
import service.Unequip;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EquipableTest {
    private final ArrayList<Item> itens = new ArrayList<>();

    @Before
    public void inicial() {
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chave").description("utilizada para abir algo").weight(0)
                .positionX(580).positionY(300).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("toch").description("utilizado para iluminar").weight(0)
                .positionX(410).positionY(220).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("tocha").description("utilizado para iluminar").weight(0)
                .positionX(410).positionY(220).image(null).removable(true).visible(true).build());
    }

    @Test
    public void validarItemEquipMochila() {
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        assertTrue(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemEquipMochilaAtributo() {
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        assertEquals(capacity + 5, player.getInventory().getMaxCapacity());
    }

    @Test
    public void validarItemUnequipMochila() {
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
        assertFalse(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemUnequipMochilaAtributo() {
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
        assertEquals(capacity, player.getInventory().getMaxCapacity());
    }

    @Test(expected = ItemEquipableException.class)
    public void validarItemUnequipMochilaAtributoErro() {
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        int size = player.getInventory().getMaxCapacity() - player.getInventory().getCapacity();
        player.getInventory().addItem(
                ItemEquipableBuilder.builder().equipped(false).name("peso").description("pesar").weight(size)
                        .positionX(410).positionY(220).image(null).removable(true).visible(true).build());
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
    }

    @Test
    public void validarItemEquipTocha() {
        Player player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).isEquipped());
    }

    @Test
    public void validarItemEquipTochaAtributo() {
        Player player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).equip(itens.get(3)));
    }

    @Test
    public void validarItemUnequipTocha() {
        Player player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
        ((ItemEquipable) itens.get(3)).unequip(itens.get(3));
        assertFalse(((ItemEquipable) itens.get(3)).isEquipped());
    }

    @Test
    public void validarItemUnequipTochaAtributo() {
        Player player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).unequip(itens.get(3)));
    }

    @Test
    public void validarItemEquip() {
        assertTrue(new Equip(itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void itemEquipInvalido() {
        assertFalse(new Equip(itens.get(2)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void naoValidarItemEquip() {
        assertFalse(new Equip(itens.get(1)).run());
    }

    @Test
    public void validarItemUnequip() {
        assertTrue(new Unequip(itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void naoValidarItemUnequipe() {
        assertFalse(new Unequip(itens.get(1)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void itemUnequipInvalido() {
        assertFalse(new Unequip(itens.get(2)).run());
    }
}
