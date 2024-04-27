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
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InteractCommandTest {

    private Map<Integer, MapGame> mapGame;
    private Map<Integer, Item> items;

    @Before
    public void create() {
        this.mapGame = new HashMap<>();
        this.mapGame.put(1, new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null)));
        this.mapGame.put(6, new MapGameFactory().create(MapGameRepository.getInstance().getById(6).orElse(null)));
        this.mapGame.put(12, new MapGameFactory().create(MapGameRepository.getInstance().getById(12).orElse(null)));
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 48, 30, "src/main/resources/image/item/mochila.png"));
        itemsE.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, null));
        itemsE.forEach(v -> this.items.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validError() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(6));
        var inventory = new Inventory(0, 0);
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.INTERACT_ERROR, msg);
    }

    @Test
    public void valid1() {
        var move = new Move("", ICoordinate.getInstance(27, 70), this.mapGame.get(12));
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(15));
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.GAME_FINISH, msg);
    }

    @Test
    public void valid2() {
        var move = new Move("", ICoordinate.getInstance(29, 72), this.mapGame.get(6));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.DOOR_OPEN, msg);
    }

    @Test
    public void valid3() {
        this.mapGame.get(1).addItem(this.items.get(10));
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(1));
        var inventory = new Inventory(0, 0);
        var player = new Player(move, inventory);
        var cmd = CommandFactory.createInteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.TAKE_ITEM, msg);
    }

}
