package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.AddItemMapGameCommand;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddItemMapGameCommandTest {

    private AddItemMapGameCommand cmd;
    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = ItemFactory.create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = MapGameFactory.create(MapGameRepository.getInstance().getById(3).orElse(null));
    }

    @Test
    public void validErro() {
        var limits = new byte[56][78];
        for (int i = 0; i < limits.length; i++) {
            for (int i1 = 0; i1 < limits[0].length; i1++) {
                limits[i][i1] = Area.CODE_ITEM;
            }
        }
        var map = new MapGame(
                Objects.requireNonNull(MapGameRepository.getInstance().getById(3).orElse(null)),
                new Area(limits), new HashMap<>(), new HashMap<>(), new ArrayList<>());
        this.cmd = new AddItemMapGameCommand(this.item, map);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.MAP_ADD_ERRO_FULL, msg);
    }

    @Test
    public void validAdd() {
        this.map.removeItem(this.item);
        this.cmd = new AddItemMapGameCommand(this.item, this.map);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ADD_ITEM_MAP, msg);
    }

    @Test
    public void validAddUndo() {
        this.map.removeItem(this.item);
        this.cmd = new AddItemMapGameCommand(this.item, this.map);
        this.cmd.execute();
        this.cmd.undo();
        assertNull(this.map.getItem(this.item.getCoordinate()));
    }
}
