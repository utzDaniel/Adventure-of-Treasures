package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandFactory;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RemoveItemInventoryCommandTest {

    private Map<Integer, Item> items;

    @Before
    public void create() {
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, "src/main/resources/image/item/chave.png"));
        itemsE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itemsE.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, "src/main/resources/image/item/tesouro.png"));
        itemsE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));

        itemsE.forEach(v -> this.items.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validError1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(15));
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(15));
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_ERROR, msg);
    }

    @Test
    public void validError2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var equible = this.items.get(10).getSpecialization(TypeItem.EQUIPPABLE);
        equible.ifPresent(specialization -> ((IEquippable) specialization).setEquip(true));
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(10));
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_ERROR_EQUIP, msg);
    }

    @Test
    public void validError3() {
        var inventory = new Inventory(0, 10);
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(10));
        var msg = cmd.execute();
        assertEquals(TypeMessage.INVENTORY_ITEM_ERROR_FOUND, msg);
    }

    @Test
    public void validEquippable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(10));
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

    @Test
    public void validUsable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(1));
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

    @Test
    public void validCombinable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(5));
        var cmd = CommandFactory.createRemoveItemInventoryCommand(inventory, this.items.get(5));
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

}
