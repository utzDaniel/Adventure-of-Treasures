package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.CommandFactory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import backend.service.model.MapGame;
import backend.service.model.MapGameFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveItemMapGameCommandTest {

    private Item item;
    private MapGame map;

    @Before
    public void create() {
        this.item = new ItemFactory().create(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        this.map = new MapGameFactory().create(MapGameRepository.getInstance().getById(3).orElse(null));
    }

    @Test
    public void validRemove() {
        this.map.addItem(this.item);
        var cmd = CommandFactory.createRemoveItemMapGameCommand(this.map, this.item);
        var msg = cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_MAP, msg);
    }

}
