package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.InteractItemCommand;
import backend.service.model.ItemFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InteractItemCommandTest {

    private InteractItemCommand cmd;
    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = new ItemFactory().create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 48, 30, "src/main/resources/image/item/mochila.png"));
        this.map = new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null));

    }

    @Test
    public void validErro() {
        this.map.addItem(this.item);
        var move = new Move("", ICoordinate.getInstance(45, 30), this.map);
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        this.cmd = new InteractItemCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ITEM_ERRO_FOUND, msg);
    }

    @Test
    public void valid() {
        this.map.addItem(this.item);
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        this.cmd = new InteractItemCommand(player);
        var msg = this.cmd.execute();
        assertNull(this.map.getItem(ICoordinate.getInstance(48, 30)));
        assertEquals(this.item, inventario.getItem(this.item.getId()));
        assertEquals(TypeMessage.TAKE_ITEM, msg);
    }

    @Test
    public void validUndo() {
        this.map.addItem(this.item);
        var move = new Move("", ICoordinate.getInstance(47, 30), this.map);
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        this.cmd = new InteractItemCommand(player);
        this.cmd.execute();
        this.cmd.undo();
        assertNull(inventario.getItem(this.item.getId()));
        assertEquals(this.item, this.map.getItem(ICoordinate.getInstance(48, 30)));
    }

}
