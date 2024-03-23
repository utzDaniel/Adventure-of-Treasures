package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.InteractCommand;
import backend.service.model.ItemFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InteractCommandTest {
    private InteractCommand cmd;

    private Map<Integer, MapGame> mapGame;
    private Map<Integer, Item> itens;

    @Before
    public void create() {
        this.mapGame = new HashMap<>();
        this.mapGame.put(1, new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null)));
        this.mapGame.put(12, new MapGameFactory().create(MapGameRepository.getInstance().getById(12).orElse(null)));
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, null));
        itensE.forEach(v -> this.itens.put(v.id(), new ItemFactory().create(v)));

    }

    @Test
    public void validErro1() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(1));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.INTERACT_ERRO, msg);
    }

    @Test
    public void validErro2() {
        var move = new Move("", ICoordinate.getInstance(28, 71), this.mapGame.get(12));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.INTERACT_ERRO_INCOMPLETE, msg);
    }

    @Test
    public void valid1() {
        var move = new Move("", ICoordinate.getInstance(28, 71), this.mapGame.get(12));
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(15));
        var player = new Player(move, inventory);
        this.cmd = new InteractCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.GAME_FINISH, msg);
    }

    @Test
    public void valid2() {
        var move = new Move("", ICoordinate.getInstance(52, 30), this.mapGame.get(1));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.INTERACT, msg);
        assertEquals(ICoordinate.getInstance(32, 7), player.getCoordinate());
        assertEquals(12, player.getCurrentMap().getId());
    }

    @Test
    public void validUndo() {
        var move = new Move("", ICoordinate.getInstance(52, 30), this.mapGame.get(1));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        var oldCoordinate = ICoordinate.getInstance(player.getCoordinate());
        this.cmd = new InteractCommand(player);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(oldCoordinate, player.getCoordinate());
        assertEquals(this.mapGame.get(1).getId(), player.getCurrentMap().getId());
    }

}
