package service;

import backend.controller.enums.TypeMessage;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.InteractDoorCommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InteractDoorCommandTest {
    private InteractDoorCommand cmd;

    private Map<Integer, MapGame> mapGame;

    @Before
    public void create() {
        this.mapGame = new HashMap<>();
        this.mapGame.put(2, new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null)));
        this.mapGame.put(6, new MapGameFactory().create(MapGameRepository.getInstance().getById(6).orElse(null)));
    }

    @Test
    public void validErro1() {
        var move = new Move("", ICoordinate.getInstance(47, 30), this.mapGame.get(2));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractDoorCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.DOOR_ERRO_FOUND, msg);
    }

    @Test
    public void validErro2() {
        var move = new Move("", ICoordinate.getInstance(13, 38), this.mapGame.get(6));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractDoorCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.DOOR_ERRO_CLOSED, msg);
    }

    @Test
    public void valid() {
        var move = new Move("", ICoordinate.getInstance(29, 72), this.mapGame.get(6));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        this.cmd = new InteractDoorCommand(player);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.DOOR_OPEN, msg);
        assertEquals(ICoordinate.getInstance(52, 37), player.getCoordinate());
        assertEquals(11, player.getCurrentMap().id());
    }

    @Test
    public void validUndo() {
        var move = new Move("", ICoordinate.getInstance(29, 72), this.mapGame.get(6));
        var inventory = new Inventory(0, 10);
        var player = new Player(move, inventory);
        var oldCoordinate = ICoordinate.getInstance(player.getCoordinate());
        this.cmd = new InteractDoorCommand(player);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(oldCoordinate, player.getCoordinate());
        assertEquals(this.mapGame.get(6).id(), player.getCurrentMap().id());
    }


}
