package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.CommandFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InteractItemCommandTest {

    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = new ItemFactory().create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 48, 30, "src/main/resources/image/item/mochila.png"));
        this.map = new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null));
    }

    @Test
    public void validError() {
        this.map.addItem(this.item);
        var move = new Move("", ICoordinate.getInstance(45, 30), this.map);
        var inventory = new Inventory(0, 0);
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractItemCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.ITEM_ERROR_FOUND, msg);
    }

    @Test
    public void valid() {
        this.map.addItem(this.item);
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventory = new Inventory(0, 0);
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractItemCommand(player);
        var msg = cmd.execute();
        assertNull(this.map.getItem(ICoordinate.getInstance(48, 30)));
        assertEquals(this.item, inventory.getItem(this.item.getId()));
        assertEquals(TypeMessage.TAKE_ITEM, msg);
    }

}
