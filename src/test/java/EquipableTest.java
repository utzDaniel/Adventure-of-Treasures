import exception.ItemEquipableException;
import exception.MoveException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import service.Equip;
import service.Unequip;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EquipableTest {
    private final ArrayList<Item> itens = new ArrayList<>();

    @Before
    public void inicial(){
        itens.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,null));
        itens.add(new ItemUsable("chave", "utilizada para abir algo", 0, "vila",580,300,null));
        itens.add(new ItemEquipable("toch", "utilizado para iluminar", 0,410,200,null));
        itens.add(new ItemEquipable("tocha", "utilizado para iluminar", 0,410,200,null));
    }

    @Test
    public void validarItemEquipMochila(){
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        assertTrue(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemEquipMochilaAtributo(){
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        assertEquals(capacity+5,player.getInventory().getMaxCapacity());
    }

    @Test
    public void validarItemUnequipMochila(){
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
        assertFalse(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemUnequipMochilaAtributo(){
        Player player = Player.getInstance();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
        assertEquals(capacity,player.getInventory().getMaxCapacity());
    }

    @Test (expected = ItemEquipableException.class)
    public void validarItemUnequipMochilaAtributoErro(){
        Player player = Player.getInstance();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0));
        int size = player.getInventory().getMaxCapacity() - player.getInventory().getCapacity();
        player.getInventory().addItem(
                new ItemEquipable("peso", "pesar",
                        size,0,0,null));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0));
    }

    @Test
    public void validarItemEquipTocha(){
        Player player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).isEquipped());
    }

    @Test
    public void validarItemEquipTochaAtributo(){
        Player player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).equip(itens.get(3)));
    }

    @Test (expected = MoveException.class)
    public void invalidarItemEquipTochaAtributo(){
        Player player = Player.getInstance();
        Scenery pier = new Scenery("cais", null);
        player.setCurrentMap(pier);
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
    }

    @Test
    public void validarItemUnequipTocha(){
        Player player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
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
    public void validarItemUnequipTochaAtributo(){
        Player player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).unequip(itens.get(3)));
    }

    @Test (expected = MoveException.class)
    public void invalidarItemUnequipTochaAtributo(){
        Player player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        ((ItemEquipable) itens.get(3)).equip(itens.get(3));

        Scenery pier = new Scenery("cais", null);
        player.setCurrentMap(pier);
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).unequip(itens.get(3));
    }


    @Test
    public void validarItemEquip(){
        assertTrue(new Equip(itens.get(0)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void itemEquipInvalido(){
        assertFalse(new Equip(itens.get(2)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void naoValidarItemEquip(){
        assertFalse(new Equip(itens.get(1)).run());
    }

    @Test
    public void validarItemUnequip(){
        assertTrue(new Unequip(itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void naoValidarItemUnequipe(){
        assertFalse(new Unequip(itens.get(1)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void itemUnequipInvalido(){
        assertFalse(new Unequip(itens.get(2)).run());
    }
}
