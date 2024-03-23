package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.AddItemInventoryCommand;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddItemInventoryCommandTest {

    private AddItemInventoryCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        var factory = new ItemFactory();
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(3, "faca", "serve para cortar algo", 30, 13, 42, "src/main/resources/image/item/faca.png"));
        itensE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itensE.forEach(v -> this.itens.put(v.id(), factory.create(v)));
    }

    @Test
    public void validErro() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(3));
        this.cmd = new AddItemInventoryCommand(this.itens.get(3), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.INVENTORY_ERRO_FULL, msg);
    }


    @Test
    public void validAdd() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new AddItemInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ADD_ITEM_INVENTORY, msg);
    }

    @Test
    public void validAddUndo() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new AddItemInventoryCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        this.cmd.undo();
        assertNull(inventory.getItem(10));
    }

}
