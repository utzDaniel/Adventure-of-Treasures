package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.CommandFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DropItemCommandTest {

    private Item item;
    private MapGame map;

    @Before
    public void create() {
        var factory = new ItemFactory();
        this.item = factory.create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null));

    }

    @Test
    public void valid() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventory = new Inventory(0, 0);
        inventory.addItem(this.item);
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.item);
        var cmd = CommandFactory.createDropItemCommand(player, list);
        var msg = cmd.execute();
        assertNull(inventory.getItem(this.item.getId()));
        assertEquals(this.item, this.map.getItem(ICoordinate.getInstance(47, 31))); //22 66
        assertEquals(TypeMessage.DROP_ITEM, msg);
    }

}
