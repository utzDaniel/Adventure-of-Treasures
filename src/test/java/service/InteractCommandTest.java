package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.*;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InteractCommandTest {

    private Map<Integer, MapGame> mapGame;
    private Map<Integer, Item> itens;

    @Before
    public void create() {
        this.mapGame = new HashMap<>();
        this.mapGame.put(1, new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null)));
        this.mapGame.put(6, new MapGameFactory().create(MapGameRepository.getInstance().getById(6).orElse(null)));
        this.mapGame.put(12, new MapGameFactory().create(MapGameRepository.getInstance().getById(12).orElse(null)));
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 48, 30, "src/main/resources/image/item/mochila.png"));
        itensE.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, null));
        itensE.forEach(v -> this.itens.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validErro() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(6));
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        var cmd = new InteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.INTERACT_ERRO, msg);
    }

    @Test
    public void valid1() {
        var move = new Move("", ICoordinate.getInstance(27, 70), this.mapGame.get(12));
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(15));
        var player = new Player(move, inventory);
        var cmd = new InteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.GAME_FINISH, msg);
    }

    @Test
    public void valid2() {
        var move = new Move("", ICoordinate.getInstance(29, 72), this.mapGame.get(6));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        var cmd = new InteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.DOOR_OPEN, msg);
    }

    @Test
    public void valid3() {
        this.mapGame.get(1).addItem(this.itens.get(10));
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(1));
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        var cmd = new InteractCommand(player);
        var msg = cmd.execute();
        assertEquals(TypeMessage.TAKE_ITEM, msg);
    }

    @Test
    public void validUndo() {
        this.mapGame.get(1).addItem(this.itens.get(10));
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(1));
        var inventario = new Inventory(0, 0);
        var player = new Player(move, inventario);
        var cmd = new InteractCommand(player);
        cmd.execute();
        cmd.undo();
        assertNull(inventario.getItem(10));
    }

}
