package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.EquippableEntity;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandFactory;
import backend.service.model.Equippable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EquippableCommandTest {

    private Map<Integer, Item> items;


    @Before
    public void create() {
        var factory = new ItemFactory();
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itemsE.add(new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 20, 41, "src/main/resources/image/item/tocha.png"));
        itemsE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itemsE.forEach(v -> this.items.put(v.id(), factory.create(v)));
    }

    @Test
    public void validError1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(5));
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        var cmd = CommandFactory.createEquippableCommand(inventory,list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.ITEM_ERROR_EQUIPPABLE, msg);
    }

    @Test
    public void validError2() {
        var inventory = new Inventory(0, 10);
        var equippable = new Equippable(new EquippableEntity(1,5,2));
        equippable.setEquip(true);
        var cmd = CommandFactory.createEquipCommand(inventory, equippable);
        var msg = cmd.execute();
        assertEquals(TypeMessage.EQUIP_ERROR, msg);
    }

    @Test
    public void validError3() {
        var inventory = new Inventory(0, 10);
        var equippable = new Equippable(new EquippableEntity(1,5,2));
        equippable.setEquip(false);
        var cmd = CommandFactory.createUnequipCommand(inventory, equippable);
        var msg = cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_ERROR, msg);
    }

    @Test
    public void validError4() {
        var factory = new ItemFactory();
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var list = new ArrayList<Item>();
        list.add(this.items.get(10));
        var cmd = CommandFactory.createEquippableCommand(inventory,list);
        cmd.execute();
        inventory.addItem(factory.create(item));
        var msg = cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_ERROR_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var list = new ArrayList<Item>();
        list.add(this.items.get(10));
        var cmd = CommandFactory.createEquippableCommand(inventory, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var list = new ArrayList<Item>();
        list.add(this.items.get(10));
        var cmd = CommandFactory.createEquippableCommand(inventory, list);
        cmd.execute();
        var msg = cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(16));
        var list = new ArrayList<Item>();
        list.add(this.items.get(16));
        var cmd = CommandFactory.createEquippableCommand(inventory, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.EQUIP_TORCH, msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(16));
        var list = new ArrayList<Item>();
        list.add(this.items.get(16));
        var cmd = CommandFactory.createEquippableCommand(inventory, list);
        cmd.execute();
        var msg = cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

}
