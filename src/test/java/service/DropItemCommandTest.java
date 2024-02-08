package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.DropItemCommand;
import backend.service.command.UsableCommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DropItemCommandTest {

    private DropItemCommand cmd;
    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = ItemFactory.create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = MapGameFactory.create(MapGameRepository.getInstance().getById(1).orElse(null));

    }

    @Test
    public void valid() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventario = new Inventory(0, 0);
        inventario.addItem(this.item);
        var player = new Player(move, inventario);
        this.cmd = new DropItemCommand(this.item, player);
        var msg = this.cmd.execute();
        assertNull(inventario.getItem(this.item.getId()));
        assertEquals(this.item, this.map.getItem(ICoordinate.getInstance(47, 31)));
        assertEquals(TypeMessage.DROP_ITEM, msg);
    }

    @Test
    public void validUndo() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventario = new Inventory(0, 0);
        inventario.addItem(this.item);
        var player = new Player(move, inventario);
        this.cmd = new DropItemCommand(this.item, player);
        this.cmd.execute();
        this.cmd.undo();
        assertNull(this.map.getItem(ICoordinate.getInstance(47, 31)));
        assertEquals(this.item, inventario.getItem(this.item.getId()));
    }

}
