package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandFactory;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UsableCommandTest {

    private Map<Integer, Item> items;

    @Before
    public void create() {
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, "src/main/resources/image/item/chave.png"));
        itemsE.add(new ItemEntity(2, "escada", "utilizada para subir em algum lugar", 5, 20, 41, "src/main/resources/image/item/escada.png"));
        itemsE.add(new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 28, 20, "src/main/resources/image/item/pa.png"));
        itemsE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itemsE.forEach(v -> this.items.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validError1() {
        var mapGame = new MapGameFactory().create(1).orElse(null);
        var move = new Move("", ICoordinate.getInstance(0, 0), mapGame);
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(5));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.ITEM_ERROR_USABLE, msg);
    }

    @Test
    public void validError2() {
        var mapGame = new MapGameFactory().create(1).orElse(null);
        var move = new Move("", ICoordinate.getInstance(15, 37), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(1));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_MAP, msg);
    }

    @Test
    public void validError3() {
        var mapGame = new MapGameFactory().create(6).orElse(null);
        var move = new Move("", ICoordinate.getInstance(10, 37), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(1));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_COORDINATE, msg);
    }

    @Test
    public void validError4() {
        var usable = this.items.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(false));
        var mapGame = new MapGameFactory().create(4).orElse(null);
        var move = new Move("", ICoordinate.getInstance(29, 56), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(11));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(11));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_ENABLE, msg);
    }

    @Test
    public void validChave() {
        var mapGame = new MapGameFactory().create(6).orElse(null);
        var move = new Move("", ICoordinate.getInstance(15, 37), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(1));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_KEY, msg);
    }

    @Test
    public void validEscada() {
        var mapGame = new MapGameFactory().create(8).orElse(null);
        var move = new Move("", ICoordinate.getInstance(19, 26), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(2));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(2));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_LADDER, msg);
    }

    @Test
    public void validPa() {
        var usable = this.items.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var mapGame = new MapGameFactory().create(4).orElse(null);
        var move = new Move("", ICoordinate.getInstance(29, 56), mapGame);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(11));
        var player = new Player(move, inventory);
        var list = new ArrayList<Item>();
        list.add(this.items.get(11));
        var cmd = CommandFactory.createUsableCommand(player, list);
        var msg = cmd.execute();
        assertEquals(TypeMessage.USABLE_SHOVEL, msg);
    }
    
}
