package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.entity.MapGameEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.CommandFactory;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AddItemMapGameCommandTest {

    private Item item;
    private MapGame map;

    @Before
    public void create() {
        var factory = new ItemFactory();
        this.item = factory.create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = new MapGameFactory().create(MapGameRepository.getInstance().getById(3).orElse(null));
    }

    @Test
    public void validError() {
        var limits = new byte[56][78];
        for (int i = 0; i < limits.length; i++) {
            for (int i1 = 0; i1 < limits[0].length; i1++) {
                limits[i][i1] = Area.CODE_BLOCK;
            }
        }
        var interact = new InteractMapGame(new HashMap<>(), new HashMap<>(), new HashMap<>());
        var map = new MapGame(new MapGameEntity(0, null, null, null, limits), new HashMap<>(), interact);
        var cmd = CommandFactory.createAddItemMapGameCommand(map, this.item);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MAP_ADD_ERROR_FULL, msg);
    }

    @Test
    public void validAdd() {
        this.map.removeItem(this.item);
        var cmd = CommandFactory.createAddItemMapGameCommand(this.map, this.item);
        var msg = cmd.execute();
        assertEquals(TypeMessage.ADD_ITEM_MAP, msg);
    }

}
