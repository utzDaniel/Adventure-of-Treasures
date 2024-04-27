package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandFactory;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AddItemInventoryCommandTest {

    private Map<Integer, Item> items;


    @Before
    public void create() {
        var factory = new ItemFactory();
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(3, "faca", "serve para cortar algo", 30, 13, 42, "src/main/resources/image/item/faca.png"));
        itemsE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itemsE.forEach(v -> this.items.put(v.id(), factory.create(v)));
    }

    @Test
    public void validError() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(3));
        var cmd = CommandFactory.createAddItemInventoryCommand(inventory, this.items.get(3));
        var msg = cmd.execute();
        assertEquals(TypeMessage.INVENTORY_ERROR_FULL, msg);
    }


    @Test
    public void validAdd() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(10));
        var cmd = CommandFactory.createAddItemInventoryCommand(inventory, this.items.get(10));
        var msg = cmd.execute();
        assertEquals(TypeMessage.ADD_ITEM_INVENTORY, msg);
    }

}
