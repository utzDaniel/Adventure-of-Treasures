package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandFactory;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CombinationCommandTest {

    private Map<Integer, Item> items;


    @Before
    public void create() {
        var factory = new ItemFactory();
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itemsE.add(new ItemEntity(12, "papel", "papel escrito em lingua antiga", 2, 32, 51, "src/main/resources/image/item/papel.png"));

        itemsE.add(new ItemEntity(7, "madeiras", "madeira para construir algo", 5, 8, 64, "src/main/resources/image/item/madeiras.png"));
        itemsE.add(new ItemEntity(9, "martelo", "utilizado para construir algo", 4, 32, 16, "src/main/resources/image/item/martelo.png"));
        itemsE.add(new ItemEntity(14, "pregos", "utilizado para construir algo", 3, 40, 46, "src/main/resources/image/item/pregos.png"));

        itemsE.add(new ItemEntity(3, "faca", "serve para cortar algo", 3, 13, 42, "src/main/resources/image/item/faca.png"));
        itemsE.add(new ItemEntity(4, "frasco", "contêm algum líquido inflamável", 3, 31, 28, "src/main/resources/image/item/frasco.png"));
        itemsE.add(new ItemEntity(6, "madeira", "cabo de madeira velho", 5, 20, 41, "src/main/resources/image/item/madeira.png"));
        itemsE.add(new ItemEntity(13, "pederneira", "item específico para fazer fogo", 2, 20, 44, "src/main/resources/image/item/pederneira.png"));

        itemsE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));

        itemsE.forEach(v -> this.items.put(v.id(), factory.create(v)));
    }

    private Inventory createInventory(List<Item> items) {
        var inventory = new Inventory(0, 15);
        items.forEach(inventory::addItem);
        return inventory;
    }

    @Test
    public void validError1() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(10));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.ITEM_ERROR_COMBINABLE, msg);
    }

    @Test
    public void validError2() {
        var factory = new ItemFactory();
        var item1 = new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png");
        var item = factory.create(item1);
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        list.add(item);
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_ERROR_ALL, msg);
    }

    @Test
    public void validError3() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_ERROR_INCOMPLETE, msg);
    }

    @Test
    public void validError4() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        list.add(this.items.get(12));
        list.add(this.items.get(7));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_ERROR_INVALID, msg);
    }

    @Test
    public void validError5() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        list.add(this.items.get(7));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_ERROR_COMBINABLE, msg);
    }

    @Test
    public void validMap() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        list.add(this.items.get(12));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_MAP, msg);
    }

    @Test
    public void validEscada() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(7));
        list.add(this.items.get(9));
        list.add(this.items.get(14));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_LADDER, msg);
    }

    @Test
    public void validTocha() {
        var list = new ArrayList<Item>();
        list.add(this.items.get(3));
        list.add(this.items.get(4));
        list.add(this.items.get(6));
        list.add(this.items.get(13));
        var cmd = CommandFactory.createCombinationCommand(createInventory(list), list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.COMBINE_TORCH, msg);
    }

}
