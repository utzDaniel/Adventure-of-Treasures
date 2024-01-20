package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.EventInventoryCommand;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventInventoryCommandTest {

    private EventInventoryCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
        var paE = new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 28, 20, "src/main/resources/image/item/pa.png");
        var pa = ItemFactory.create(paE);
        this.itens.put(pa.getId(), pa);
    }

    @Test
    public void validEventPa1() {
        var inventory = new Inventory(0, 10);
        this.cmd = new EventInventoryCommand(this.itens.get(11), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EVENT_INVENTORY_KEY), msg);
    }

    @Test
    public void validEventFull() {
        var inventory = new Inventory(0, 10);
        var item1 = new ItemEntity(7, "madeiras", "madeira para construir algo", 10, 8, 64, "src/main/resources/image/item/madeiras.png");
        var item = ItemFactory.create(item1);
        inventory.addItem(item);
        this.cmd = new EventInventoryCommand(this.itens.get(11), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.INVENTORY_ERRO_FULL), msg);
    }

}
