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
        itens.add(new ItemUsable("chave", "utilizada para abir algo", 3, "vila",580,300,null));
        itens.add(new ItemEquipable("toch", "utilizado para iluminar", 5,410,200,null));
        itens.add(new ItemEquipable("tocha", "utilizado para iluminar", 5,410,200,null));
    }

    @Test
    public void validarItemEquipMochila(){
        Player player = new Player();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0), player);
        assertTrue(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemEquipMochilaAtributo(){
        Player player = new Player();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0), player);
        assertEquals(capacity+5,player.getInventory().getMaxCapacity());
    }

    @Test
    public void validarItemUnequipMochila(){
        Player player = new Player();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0), player);
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0), player);
        assertFalse(((ItemEquipable) itens.get(0)).isEquipped());
    }

    @Test
    public void validarItemUnequipMochilaAtributo(){
        Player player = new Player();
        int capacity = player.getInventory().getMaxCapacity();
        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0), player);
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0), player);
        assertEquals(capacity,player.getInventory().getMaxCapacity());
    }

    @Test (expected = ItemEquipableException.class)
    public void validarItemUnequipMochilaAtributoErro(){
        Player player = new Player();

        player.getInventory().addItem(itens.get(0));
        ((ItemEquipable) itens.get(0)).equip(itens.get(0), player);
        player.getInventory().addItem(
                new ItemEquipable("peso", "pesar",
                        player.getInventory().getMaxCapacity(),0,0,null));
        ((ItemEquipable) itens.get(0)).unequip(itens.get(0), player);
    }

    @Test
    public void validarItemEquipTocha(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3), player);
        assertTrue(((ItemEquipable) itens.get(3)).isEquipped());
    }

    @Test
    public void validarItemEquipTochaAtributo(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        assertTrue(((ItemEquipable) itens.get(3)).equip(itens.get(3), player));
    }

    @Test (expected = MoveException.class)
    public void invalidarItemEquipTochaAtributo(){
        Player player = new Player();
        Scenery pier = new Scenery("cais", null);
        player.setCurrentMap(pier);
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3), player);
    }

    @Test
    public void validarItemUnequipTocha(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3), player);
        ((ItemEquipable) itens.get(3)).unequip(itens.get(3), player);
        assertFalse(((ItemEquipable) itens.get(3)).isEquipped());
    }

    @Test
    public void validarItemUnequipTochaAtributo(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).equip(itens.get(3), player);
        assertTrue(((ItemEquipable) itens.get(3)).unequip(itens.get(3), player));
    }

    @Test (expected = MoveException.class)
    public void invalidarItemUnequipTochaAtributo(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        ((ItemEquipable) itens.get(3)).equip(itens.get(3), player);

        Scenery pier = new Scenery("cais", null);
        player.setCurrentMap(pier);
        player.getInventory().addItem(itens.get(3));
        ((ItemEquipable) itens.get(3)).unequip(itens.get(3), player);
    }


    @Test
    public void validarItemEquip(){
        assertTrue(new Equip(new Player(),itens.get(0)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void itemEquipInvalido(){
        assertFalse(new Equip(new Player(),itens.get(2)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void naoValidarItemEquip(){
        assertFalse(new Equip(new Player(),itens.get(1)).run());
    }

    @Test
    public void validarItemUnequip(){
        assertTrue(new Unequip(new Player(),itens.get(0)).run());
    }

    @Test(expected = ItemEquipableException.class)
    public void naoValidarItemUnequipe(){
        assertFalse(new Unequip(new Player(),itens.get(1)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void itemUnequipInvalido(){
        assertFalse(new Unequip(new Player(),itens.get(2)).run());
    }
}
