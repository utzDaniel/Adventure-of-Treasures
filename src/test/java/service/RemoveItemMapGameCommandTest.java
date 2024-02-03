package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.AddItemMapGameCommand;
import backend.service.command.RemoveItemInventoryCommand;
import backend.service.command.RemoveItemMapGameCommand;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoveItemMapGameCommandTest {

    private RemoveItemMapGameCommand cmd;
    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = ItemFactory.create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = MapGameFactory.create(MapGameRepository.getInstance().getById(3).orElse(null));
    }

    @Test
    public void validRemove() {
        this.map.addItem(this.item);
        this.cmd = new RemoveItemMapGameCommand(this.item, this.map);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_MAP, msg);
    }

    @Test
    public void validRemoveUndo() {
        this.map.addItem(this.item);
        this.cmd = new RemoveItemMapGameCommand(this.item, this.map);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(this.item, this.map.getItem(this.item.getCoordinate()));
    }

}
